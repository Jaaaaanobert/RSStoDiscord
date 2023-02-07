This is a simple tool to send the title and the link (GUID) of an RSS-Feed entry to a discord webhook.

The program automatically checks the RSS-Feed for new entries and automatically sends them to the webhook.
In the config file you can configure the URL of your Discord-Webhook, the URL of the RSS-Feed and the sync-inverval (in min).
The time of the latest sync is stored in the config property in millis.

The program can check multiple feed at the same time, which you can configure with the number of the property “instances”.
For every new instance n you have to add the fields instancename-n, discordurl-n, feedurl-n (while n is the number of the instance you want to configure)

The program is a console application, there is no GUI.
