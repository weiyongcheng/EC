package com.wei.compiler;

import com.google.auto.service.AutoService;
import com.wei.annotation.AppRegisterGenerator;
import com.wei.annotation.EntryGenerator;
import com.wei.annotation.PayEntryGenerator;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.AnnotationValueVisitor;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

/**
 * Created by Administrator on 2017/10/27.
 */

@AutoService(Processor.class)
public class LatteProcesser extends AbstractProcessor {

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        final Set<String> types = new LinkedHashSet<>();
        final Set<Class<? extends Annotation>> supportAnnotations = getSupportedAnnotations();
        for (Class<? extends Annotation> annotaions: supportAnnotations) {
            types.add(annotaions.getCanonicalName());
        }
        return types;
    }

    private Set<Class<? extends Annotation>> getSupportedAnnotations() {
        final Set<Class<? extends Annotation>> annotation = new LinkedHashSet<>();
        annotation.add(EntryGenerator.class);
        annotation.add(PayEntryGenerator.class);
        annotation.add(AppRegisterGenerator.class);
        return annotation;
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        generateEntryCode(roundEnvironment);
        generatePayEntryCode(roundEnvironment);
        generateAppRegisterEntryCode(roundEnvironment);

        return true;
    }

    private void scan(RoundEnvironment env,
                      Class<? extends Annotation> anntations,
                      AnnotationValueVisitor visitor) {
        for (Element typeElement:env.getElementsAnnotatedWith(anntations)) {
            final List<? extends AnnotationMirror> anntationMirrors =
                    typeElement.getAnnotationMirrors();

            for (AnnotationMirror annotationMirror: anntationMirrors) {
                final Map<? extends ExecutableElement, ? extends AnnotationValue> elementValues
                        = annotationMirror.getElementValues();

                for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry:
                        elementValues.entrySet()) {
                    entry.getValue().accept(visitor, null);
                }
            }
        }
    }

    private void generateEntryCode(RoundEnvironment env) {
        final EntryVisiitor entryVisiitor = new EntryVisiitor();
        entryVisiitor.setFiler(processingEnv.getFiler());
        scan(env, EntryGenerator.class, entryVisiitor);
    }

    private void generatePayEntryCode(RoundEnvironment env) {
        final PayEntryVisiitor payEntryVisiitor = new PayEntryVisiitor();
        payEntryVisiitor.setFiler(processingEnv.getFiler());
        scan(env, PayEntryGenerator.class, payEntryVisiitor);
    }

    private void generateAppRegisterEntryCode(RoundEnvironment env) {
        final AppRegisterEntryVisiitor appRegisterEntryVisiitor = new AppRegisterEntryVisiitor();
        appRegisterEntryVisiitor.setFiler(processingEnv.getFiler());
        scan(env, AppRegisterGenerator.class, appRegisterEntryVisiitor);
    }
}
