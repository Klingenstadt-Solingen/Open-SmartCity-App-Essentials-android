package de.osca.android.essentials.presentation.component.design

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import de.osca.android.essentials.R

@Composable
fun ContactEmailPhoneWebsite(
    masterDesignArgs: MasterDesignArgs,
    moduleDesignArgs: ModuleDesignArgs,
    email: String,
    phone: String,
    url: String,
    withTitle: Boolean = true,
    subject: String = stringResource(id = R.string.global_mail_subject),
    context: Context,
    additionalContent: (@Composable () -> Unit)? = null
) {
    BaseCardContainer(
        text = stringResource(id = R.string.global_contact),
        moduleDesignArgs = moduleDesignArgs,
        masterDesignArgs = masterDesignArgs
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            OpenEmailElement(
                email = email,
                subject = subject,
                context = context,
                withTitle = withTitle,
                masterDesignArgs = masterDesignArgs,
                moduleDesignArgs = moduleDesignArgs
            )

            OpenPhoneElement(
                phone = phone,
                context = context,
                withTitle = withTitle,
                masterDesignArgs = masterDesignArgs,
                moduleDesignArgs = moduleDesignArgs
            )

            OpenWebsiteElement(
                url = url,
                context = context,
                withTitle = withTitle,
                masterDesignArgs = masterDesignArgs,
                moduleDesignArgs = moduleDesignArgs
            )

            if(additionalContent != null) {
                additionalContent()
            }
        }
    }
}