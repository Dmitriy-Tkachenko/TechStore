package ru.tk4dmitriy.features.products.di

import ru.tk4dmitriy.features.products.api.FeatureProductsApi
import ru.tk4dmitriy.module_injector.ComponentHolder

object FeatureProductsComponentHolder :
    ComponentHolder<FeatureProductsApi, FeatureProductsDependencies> {
    private var featureProductsComponent: FeatureProductsComponent? = null

   override fun init(dependencies: FeatureProductsDependencies) {
        if (featureProductsComponent == null) {
            synchronized(FeatureProductsComponentHolder::class.java) {
                if (featureProductsComponent == null) {
                    featureProductsComponent = FeatureProductsComponent.initAndGet(dependencies)
                }
            }
        }
    }

    override fun get(): FeatureProductsApi = getComponent()

    internal fun getComponent(): FeatureProductsComponent {
        checkNotNull(featureProductsComponent) { "FeatureProductsComponent was not initialized!" }
        return featureProductsComponent!!
    }

    override fun reset() {
        featureProductsComponent = null
    }
}