package com.puppycrawl.tools.checkstyle.permission;

@SuppressWarnings({"unchecked", "deprecation", "all"})
public final class Manifest {

    public Manifest() {
        throw new RuntimeException("Stub!");
    }

    @SuppressWarnings({"unchecked", "deprecation", "all"})
    public static final class permission {

        public permission() {
            throw new RuntimeException("Stub!");
        }

        /**
         * Allows a calling app to continue a call which was started in another app.  An example is a
         * video calling app that wants to continue a voice call on the user's mobile network.<p>
         * When the handover of a call from one app to another takes place, there are two devices
         * which are involved in the handover; the initiating and receiving devices.  The initiating
         * device is where the request to handover the call was started, and the receiving device is
         * where the handover request is confirmed by the other party.<p>
         * This permission protects access to the
         * {@link android.telecom.TelecomManager#acceptHandover(Uri, int, PhoneAccountHandle)} which
         * the receiving side of the handover uses to accept a handover.
         * <p>Protection level: dangerous
         */

        public static final java.lang.String ACCEPT_HANDOVER = "android.permission.ACCEPT_HANDOVER";

        /**
         * Allows read/write access to the "properties" table in the checkin
         * database, to change values that get uploaded.
         * <p>Not for use by third-party applications.
         */

        public static final java.lang.String ACCESS_CHECKIN_PROPERTIES = "android.permission.ACCESS_CHECKIN_PROPERTIES";

        /**
         * Allows an app to access approximate location.
         * Alternatively, you might want {@link #ACCESS_FINE_LOCATION}.
         * <p>Protection level: dangerous
         */

        public static final java.lang.String ACCESS_COARSE_LOCATION = "android.permission.ACCESS_COARSE_LOCATION";

        /**
         * Allows an app to access precise location.
         * Alternatively, you might want {@link #ACCESS_COARSE_LOCATION}.
         * <p>Protection level: dangerous
         */

        public static final java.lang.String ACCESS_FINE_LOCATION = "android.permission.ACCESS_FINE_LOCATION";

        /**
         * Allows an application to access extra location provider commands.
         * <p>Protection level: normal
         */

        public static final java.lang.String ACCESS_LOCATION_EXTRA_COMMANDS = "android.permission.ACCESS_LOCATION_EXTRA_COMMANDS";

        /**
         * Allows applications to access information about networks.
         * <p>Protection level: normal
         */

        public static final java.lang.String ACCESS_NETWORK_STATE = "android.permission.ACCESS_NETWORK_STATE";

        /**
         * Marker permission for applications that wish to access notification policy.
         * <p>Protection level: normal
         */

        public static final java.lang.String ACCESS_NOTIFICATION_POLICY = "android.permission.ACCESS_NOTIFICATION_POLICY";

        /**
         * Allows applications to access information about Wi-Fi networks.
         * <p>Protection level: normal
         */

        public static final java.lang.String ACCESS_WIFI_STATE = "android.permission.ACCESS_WIFI_STATE";

        /**
         * Allows applications to call into AccountAuthenticators.
         * <p>Not for use by third-party applications.
         */

        public static final java.lang.String ACCOUNT_MANAGER = "android.permission.ACCOUNT_MANAGER";

        /**
         * Allows an application to add voicemails into the system.
         * <p>Protection level: dangerous
         */

        public static final java.lang.String ADD_VOICEMAIL = "com.android.voicemail.permission.ADD_VOICEMAIL";

        /**
         * Allows the app to answer an incoming phone call.
         * <p>Protection level: dangerous
         */

        public static final java.lang.String ANSWER_PHONE_CALLS = "android.permission.ANSWER_PHONE_CALLS";

        /**
         * Allows an application to collect battery statistics
         */

        public static final java.lang.String BATTERY_STATS = "android.permission.BATTERY_STATS";

        /**
         * Must be required by an {@link android.accessibilityservice.AccessibilityService},
         * to ensure that only the system can bind to it.
         * <p>Protection level: signature
         */

        public static final java.lang.String BIND_ACCESSIBILITY_SERVICE = "android.permission.BIND_ACCESSIBILITY_SERVICE";

        /**
         * Allows an application to tell the AppWidget service which application
         * can access AppWidget's data.  The normal user flow is that a user
         * picks an AppWidget to go into a particular host, thereby giving that
         * host application access to the private data from the AppWidget app.
         * An application that has this permission should honor that contract.
         * <p>Not for use by third-party applications.
         */

        public static final java.lang.String BIND_APPWIDGET = "android.permission.BIND_APPWIDGET";

        /**
         * Must be required by a {@link android.service.autofill.AutofillService},
         * to ensure that only the system can bind to it.
         * <p>Protection level: signature
         */

        public static final java.lang.String BIND_AUTOFILL_SERVICE = "android.permission.BIND_AUTOFILL_SERVICE";

        /**
         * @deprecated Use {@link android.Manifest.permission#BIND_CARRIER_SERVICES} instead
         */

        @Deprecated
        public static final java.lang.String BIND_CARRIER_MESSAGING_SERVICE = "android.permission.BIND_CARRIER_MESSAGING_SERVICE";

        /**
         * The system process that is allowed to bind to services in carrier apps will
         * have this permission. Carrier apps should use this permission to protect
         * their services that only the system is allowed to bind to.
         * <p>Protection level: signature|privileged
         */

        public static final java.lang.String BIND_CARRIER_SERVICES = "android.permission.BIND_CARRIER_SERVICES";

        /**
         * Must be required by a {@link
         * android.service.chooser.ChooserTargetService}, to ensure that
         * only the system can bind to it.
         * <p>Protection level: signature
         */

        public static final java.lang.String BIND_CHOOSER_TARGET_SERVICE = "android.permission.BIND_CHOOSER_TARGET_SERVICE";

        /**
         * Must be required by a {@link
         * android.service.notification.ConditionProviderService},
         * to ensure that only the system can bind to it.
         * <p>Protection level: signature
         */

        public static final java.lang.String BIND_CONDITION_PROVIDER_SERVICE = "android.permission.BIND_CONDITION_PROVIDER_SERVICE";

        /**
         * Must be required by device administration receiver, to ensure that only the
         * system can interact with it.
         * <p>Protection level: signature
         */

        public static final java.lang.String BIND_DEVICE_ADMIN = "android.permission.BIND_DEVICE_ADMIN";

        /**
         * Must be required by an {@link android.service.dreams.DreamService},
         * to ensure that only the system can bind to it.
         * <p>Protection level: signature
         */

        public static final java.lang.String BIND_DREAM_SERVICE = "android.permission.BIND_DREAM_SERVICE";

        /**
         * Must be required by a {@link android.telecom.InCallService},
         * to ensure that only the system can bind to it.
         * <p>Protection level: signature|privileged
         */

        public static final java.lang.String BIND_INCALL_SERVICE = "android.permission.BIND_INCALL_SERVICE";

        /**
         * Must be required by an {@link android.inputmethodservice.InputMethodService},
         * to ensure that only the system can bind to it.
         * <p>Protection level: signature
         */

        public static final java.lang.String BIND_INPUT_METHOD = "android.permission.BIND_INPUT_METHOD";

        /**
         * Must be required by an {@link android.media.midi.MidiDeviceService},
         * to ensure that only the system can bind to it.
         * <p>Protection level: signature
         */

        public static final java.lang.String BIND_MIDI_DEVICE_SERVICE = "android.permission.BIND_MIDI_DEVICE_SERVICE";

        /**
         * Must be required by a {@link android.nfc.cardemulation.HostApduService}
         * or {@link android.nfc.cardemulation.OffHostApduService} to ensure that only
         * the system can bind to it.
         * <p>Protection level: signature
         */

        public static final java.lang.String BIND_NFC_SERVICE = "android.permission.BIND_NFC_SERVICE";

        /**
         * Must be required by an {@link
         * android.service.notification.NotificationListenerService},
         * to ensure that only the system can bind to it.
         * <p>Protection level: signature
         */

        public static final java.lang.String BIND_NOTIFICATION_LISTENER_SERVICE = "android.permission.BIND_NOTIFICATION_LISTENER_SERVICE";

        /**
         * Must be required by a {@link android.printservice.PrintService},
         * to ensure that only the system can bind to it.
         * <p>Protection level: signature
         */

        public static final java.lang.String BIND_PRINT_SERVICE = "android.permission.BIND_PRINT_SERVICE";

        /**
         * Allows an application to bind to third party quick settings tiles.
         * <p>Should only be requested by the System, should be required by
         * TileService declarations.
         */

        public static final java.lang.String BIND_QUICK_SETTINGS_TILE = "android.permission.BIND_QUICK_SETTINGS_TILE";

        /**
         * Must be required by a {@link android.widget.RemoteViewsService},
         * to ensure that only the system can bind to it.
         */

        public static final java.lang.String BIND_REMOTEVIEWS = "android.permission.BIND_REMOTEVIEWS";

        /**
         * Must be required by a {@link android.telecom.CallScreeningService},
         * to ensure that only the system can bind to it.
         * <p>Protection level: signature|privileged
         */

        public static final java.lang.String BIND_SCREENING_SERVICE = "android.permission.BIND_SCREENING_SERVICE";

        /**
         * Must be required by a {@link android.telecom.ConnectionService},
         * to ensure that only the system can bind to it.
         * <p>Protection level: signature|privileged
         */

        public static final java.lang.String BIND_TELECOM_CONNECTION_SERVICE = "android.permission.BIND_TELECOM_CONNECTION_SERVICE";

        /**
         * Must be required by a TextService (e.g.&nbsp;SpellCheckerService)
         * to ensure that only the system can bind to it.
         * <p>Protection level: signature
         */

        public static final java.lang.String BIND_TEXT_SERVICE = "android.permission.BIND_TEXT_SERVICE";

        /**
         * Must be required by a {@link android.media.tv.TvInputService}
         * to ensure that only the system can bind to it.
         * <p>Protection level: signature|privileged
         */

        public static final java.lang.String BIND_TV_INPUT = "android.permission.BIND_TV_INPUT";

        /**
         * Must be required by a link {@link android.telephony.VisualVoicemailService} to ensure that
         * only the system can bind to it.
         * <p>Protection level: signature|privileged
         */

        public static final java.lang.String BIND_VISUAL_VOICEMAIL_SERVICE = "android.permission.BIND_VISUAL_VOICEMAIL_SERVICE";

        /**
         * Must be required by a {@link android.service.voice.VoiceInteractionService},
         * to ensure that only the system can bind to it.
         * <p>Protection level: signature
         */

        public static final java.lang.String BIND_VOICE_INTERACTION = "android.permission.BIND_VOICE_INTERACTION";

        /**
         * Must be required by a {@link android.net.VpnService},
         * to ensure that only the system can bind to it.
         * <p>Protection level: signature
         */

        public static final java.lang.String BIND_VPN_SERVICE = "android.permission.BIND_VPN_SERVICE";

        /**
         * Must be required by an {@link android.service.vr.VrListenerService}, to ensure that only
         * the system can bind to it.
         * <p>Protection level: signature
         */

        public static final java.lang.String BIND_VR_LISTENER_SERVICE = "android.permission.BIND_VR_LISTENER_SERVICE";

        /**
         * Must be required by a {@link android.service.wallpaper.WallpaperService},
         * to ensure that only the system can bind to it.
         * <p>Protection level: signature|privileged
         */

        public static final java.lang.String BIND_WALLPAPER = "android.permission.BIND_WALLPAPER";

        /**
         * Allows applications to connect to paired bluetooth devices.
         * <p>Protection level: normal
         */

        public static final java.lang.String BLUETOOTH = "android.permission.BLUETOOTH";

        /**
         * Allows applications to discover and pair bluetooth devices.
         * <p>Protection level: normal
         */

        public static final java.lang.String BLUETOOTH_ADMIN = "android.permission.BLUETOOTH_ADMIN";

        /**
         * Allows applications to pair bluetooth devices without user interaction, and to
         * allow or disallow phonebook access or message access.
         * This is not available to third party applications.
         */

        public static final java.lang.String BLUETOOTH_PRIVILEGED = "android.permission.BLUETOOTH_PRIVILEGED";

        /**
         * Allows an application to access data from sensors that the user uses to
         * measure what is happening inside his/her body, such as heart rate.
         * <p>Protection level: dangerous
         */

        public static final java.lang.String BODY_SENSORS = "android.permission.BODY_SENSORS";

        /**
         * Allows an application to broadcast a notification that an application
         * package has been removed.
         * <p>Not for use by third-party applications.
         */

        public static final java.lang.String BROADCAST_PACKAGE_REMOVED = "android.permission.BROADCAST_PACKAGE_REMOVED";

        /**
         * Allows an application to broadcast an SMS receipt notification.
         * <p>Not for use by third-party applications.
         */

        public static final java.lang.String BROADCAST_SMS = "android.permission.BROADCAST_SMS";

        /**
         * Allows an application to broadcast sticky intents.  These are
         * broadcasts whose data is held by the system after being finished,
         * so that clients can quickly retrieve that data without having
         * to wait for the next broadcast.
         * <p>Protection level: normal
         */

        public static final java.lang.String BROADCAST_STICKY = "android.permission.BROADCAST_STICKY";

        /**
         * Allows an application to broadcast a WAP PUSH receipt notification.
         * <p>Not for use by third-party applications.
         */

        public static final java.lang.String BROADCAST_WAP_PUSH = "android.permission.BROADCAST_WAP_PUSH";

        /**
         * Allows an application to initiate a phone call without going through
         * the Dialer user interface for the user to confirm the call.
         * <p>Protection level: dangerous
         */

        public static final java.lang.String CALL_PHONE = "android.permission.CALL_PHONE";

        /**
         * Allows an application to call any phone number, including emergency
         * numbers, without going through the Dialer user interface for the user
         * to confirm the call being placed.
         * <p>Not for use by third-party applications.
         */

        public static final java.lang.String CALL_PRIVILEGED = "android.permission.CALL_PRIVILEGED";

        /**
         * Required to be able to access the camera device.
         * <p>This will automatically enforce the <a
         * href="{@docRoot}guide/topics/manifest/uses-feature-element.html">
         * <uses-feature>}</a> manifest element for <em>all</em> camera features.
         * If you do not require all camera features or can properly operate if a camera
         * is not available, then you must modify your manifest as appropriate in order to
         * install on devices that don't support all camera features.</p>
         * <p>Protection level: dangerous
         */

        public static final java.lang.String CAMERA = "android.permission.CAMERA";

        /**
         * Allows an application to capture audio output.
         * <p>Not for use by third-party applications.</p>
         */

        public static final java.lang.String CAPTURE_AUDIO_OUTPUT = "android.permission.CAPTURE_AUDIO_OUTPUT";

        /**
         * Allows an application to capture secure video output.
         * <p>Not for use by third-party applications.</p>
         */

        public static final java.lang.String CAPTURE_SECURE_VIDEO_OUTPUT = "android.permission.CAPTURE_SECURE_VIDEO_OUTPUT";

        /**
         * Allows an application to capture video output.
         * <p>Not for use by third-party applications.</p>
         */

        public static final java.lang.String CAPTURE_VIDEO_OUTPUT = "android.permission.CAPTURE_VIDEO_OUTPUT";

        /**
         * Allows an application to change whether an application component (other than its own) is
         * enabled or not.
         * <p>Not for use by third-party applications.
         */

        public static final java.lang.String CHANGE_COMPONENT_ENABLED_STATE = "android.permission.CHANGE_COMPONENT_ENABLED_STATE";

        /**
         * Allows an application to modify the current configuration, such
         * as locale.
         */

        public static final java.lang.String CHANGE_CONFIGURATION = "android.permission.CHANGE_CONFIGURATION";

        /**
         * Allows applications to change network connectivity state.
         * <p>Protection level: normal
         */

        public static final java.lang.String CHANGE_NETWORK_STATE = "android.permission.CHANGE_NETWORK_STATE";

        /**
         * Allows applications to enter Wi-Fi Multicast mode.
         * <p>Protection level: normal
         */

        public static final java.lang.String CHANGE_WIFI_MULTICAST_STATE = "android.permission.CHANGE_WIFI_MULTICAST_STATE";

        /**
         * Allows applications to change Wi-Fi connectivity state.
         * <p>Protection level: normal
         */

        public static final java.lang.String CHANGE_WIFI_STATE = "android.permission.CHANGE_WIFI_STATE";

        /**
         * Allows an application to clear the caches of all installed
         * applications on the device.
         * <p>Protection level: signature|privileged
         */

        public static final java.lang.String CLEAR_APP_CACHE = "android.permission.CLEAR_APP_CACHE";

        /**
         * Allows enabling/disabling location update notifications from
         * the radio.
         * <p>Not for use by third-party applications.
         */

        public static final java.lang.String CONTROL_LOCATION_UPDATES = "android.permission.CONTROL_LOCATION_UPDATES";

        /**
         * Old permission for deleting an app's cache files, no longer used,
         * but signals for us to quietly ignore calls instead of throwing an exception.
         */

        public static final java.lang.String DELETE_CACHE_FILES = "android.permission.DELETE_CACHE_FILES";

        /**
         * Allows an application to delete packages.
         * <p>Not for use by third-party applications.
         * <p>Starting in {@link android.os.Build.VERSION_CODES#N}, user confirmation is requested
         * when the application deleting the package is not the same application that installed the
         * package.
         */

        public static final java.lang.String DELETE_PACKAGES = "android.permission.DELETE_PACKAGES";

        /**
         * Allows applications to RW to diagnostic resources.
         * <p>Not for use by third-party applications.
         */

        public static final java.lang.String DIAGNOSTIC = "android.permission.DIAGNOSTIC";

        /**
         * Allows applications to disable the keyguard if it is not secure.
         * <p>Protection level: normal
         */

        public static final java.lang.String DISABLE_KEYGUARD = "android.permission.DISABLE_KEYGUARD";

        /**
         * Allows an application to retrieve state dump information from system services.
         * <p>Not for use by third-party applications.
         */

        public static final java.lang.String DUMP = "android.permission.DUMP";

        /**
         * Allows an application to expand or collapse the status bar.
         * <p>Protection level: normal
         */

        public static final java.lang.String EXPAND_STATUS_BAR = "android.permission.EXPAND_STATUS_BAR";

        /**
         * Run as a manufacturer test application, running as the root user.
         * Only available when the device is running in manufacturer test mode.
         * <p>Not for use by third-party applications.
         */

        public static final java.lang.String FACTORY_TEST = "android.permission.FACTORY_TEST";

        /**
         * Allows a regular application to use {@link android.app.Service#startForeground
         * Service.startForeground}.
         * <p>Protection level: normal
         */

        public static final java.lang.String FOREGROUND_SERVICE = "android.permission.FOREGROUND_SERVICE";

        /**
         * Allows access to the list of accounts in the Accounts Service.
         * <p class="note"><strong>Note:</strong> Beginning with Android 6.0 (API level
         * 23), if an app shares the signature of the authenticator that manages an
         * account, it does not need <code>"GET_ACCOUNTS"</code> permission to read
         * information about that account. On Android 5.1 and lower, all apps need
         * <code>"GET_ACCOUNTS"</code> permission to read information about any
         * account.</p>
         * <p>Protection level: dangerous
         */

        public static final java.lang.String GET_ACCOUNTS = "android.permission.GET_ACCOUNTS";

        /**
         * Allows access to the list of accounts in the Accounts Service.
         */

        public static final java.lang.String GET_ACCOUNTS_PRIVILEGED = "android.permission.GET_ACCOUNTS_PRIVILEGED";

        /**
         * Allows an application to find out the space used by any package.
         * <p>Protection level: normal
         */

        public static final java.lang.String GET_PACKAGE_SIZE = "android.permission.GET_PACKAGE_SIZE";

        /**
         * @deprecated No longer enforced.
         */

        @Deprecated
        public static final java.lang.String GET_TASKS = "android.permission.GET_TASKS";

        /**
         * This permission can be used on content providers to allow the global
         * search system to access their data.  Typically it used when the
         * provider has some permissions protecting it (which global search
         * would not be expected to hold), and added as a read-only permission
         * to the path in the provider where global search queries are
         * performed.  This permission can not be held by regular applications;
         * it is used by applications to protect themselves from everyone else
         * besides global search.
         */

        public static final java.lang.String GLOBAL_SEARCH = "android.permission.GLOBAL_SEARCH";

        /**
         * Allows an application to install a location provider into the Location Manager.
         * <p>Not for use by third-party applications.
         */

        public static final java.lang.String INSTALL_LOCATION_PROVIDER = "android.permission.INSTALL_LOCATION_PROVIDER";

        /**
         * Allows an application to install packages.
         * <p>Not for use by third-party applications.
         */

        public static final java.lang.String INSTALL_PACKAGES = "android.permission.INSTALL_PACKAGES";

        /**
         * Allows an application to install a shortcut in Launcher.
         * <p>In Android O (API level 26) and higher, the <code>INSTALL_SHORTCUT</code> broadcast no
         * longer has any effect on your app because it's a private, implicit
         * broadcast. Instead, you should create an app shortcut by using the
         * {@link android.content.pm.ShortcutManager#requestPinShortcut requestPinShortcut()}
         * method from the {@link android.content.pm.ShortcutManager} class.
         * <p>Protection level: normal
         */

        public static final java.lang.String INSTALL_SHORTCUT = "com.android.launcher.permission.INSTALL_SHORTCUT";

        /**
         * Allows an instant app to create foreground services.
         */

        public static final java.lang.String INSTANT_APP_FOREGROUND_SERVICE = "android.permission.INSTANT_APP_FOREGROUND_SERVICE";

        /**
         * Allows applications to open network sockets.
         * <p>Protection level: normal
         */

        public static final java.lang.String INTERNET = "android.permission.INTERNET";

        /**
         * Allows an application to call
         * {@link android.app.ActivityManager#killBackgroundProcesses}.
         * <p>Protection level: normal
         */

        public static final java.lang.String KILL_BACKGROUND_PROCESSES = "android.permission.KILL_BACKGROUND_PROCESSES";

        /**
         * Allows an application to use location features in hardware,
         * such as the geofencing api.
         * <p>Not for use by third-party applications.
         */

        public static final java.lang.String LOCATION_HARDWARE = "android.permission.LOCATION_HARDWARE";

        /**
         * Allows an application to manage access to documents, usually as part
         * of a document picker.
         * <p>This permission should <em>only</em> be requested by the platform
         * document management app.  This permission cannot be granted to
         * third-party apps.
         * <p>Protection level: signature
         */

        public static final java.lang.String MANAGE_DOCUMENTS = "android.permission.MANAGE_DOCUMENTS";

        /**
         * Allows a calling application which manages it own calls through the self-managed
         * {@link android.telecom.ConnectionService} APIs.  See
         * {@link android.telecom.PhoneAccount#CAPABILITY_SELF_MANAGED for more information on the
         * self-managed ConnectionService APIs.
         * <p>Protection level: normal
         */

        public static final java.lang.String MANAGE_OWN_CALLS = "android.permission.MANAGE_OWN_CALLS";

        /**
         * Not for use by third-party applications.
         */

        public static final java.lang.String MASTER_CLEAR = "android.permission.MASTER_CLEAR";

        /**
         * Allows an application to know what content is playing and control its playback.
         * <p>Not for use by third-party applications due to privacy of media consumption</p>
         */

        public static final java.lang.String MEDIA_CONTENT_CONTROL = "android.permission.MEDIA_CONTENT_CONTROL";

        /**
         * Allows an application to modify global audio settings.
         * <p>Protection level: normal
         */

        public static final java.lang.String MODIFY_AUDIO_SETTINGS = "android.permission.MODIFY_AUDIO_SETTINGS";

        /**
         * Allows modification of the telephony state - power on, mmi, etc.
         * Does not include placing calls.
         * <p>Not for use by third-party applications.
         */

        public static final java.lang.String MODIFY_PHONE_STATE = "android.permission.MODIFY_PHONE_STATE";

        /**
         * Allows formatting file systems for removable storage.
         * <p>Not for use by third-party applications.
         */

        public static final java.lang.String MOUNT_FORMAT_FILESYSTEMS = "android.permission.MOUNT_FORMAT_FILESYSTEMS";

        /**
         * Allows mounting and unmounting file systems for removable storage.
         * <p>Not for use by third-party applications.
         */

        public static final java.lang.String MOUNT_UNMOUNT_FILESYSTEMS = "android.permission.MOUNT_UNMOUNT_FILESYSTEMS";

        /**
         * Allows applications to perform I/O operations over NFC.
         * <p>Protection level: normal
         */

        public static final java.lang.String NFC = "android.permission.NFC";

        /**
         * Allows applications to receive NFC transaction events.
         * <p>Protection level: normal
         */

        public static final java.lang.String NFC_TRANSACTION_EVENT = "android.permission.NFC_TRANSACTION_EVENT";

        /**
         * Allows an application to collect component usage
         * statistics
         * <p>Declaring the permission implies intention to use the API and the user of the
         * device can grant permission through the Settings application.
         */

        public static final java.lang.String PACKAGE_USAGE_STATS = "android.permission.PACKAGE_USAGE_STATS";

        /**
         * @deprecated This functionality will be removed in the future; please do
         * not use. Allow an application to make its activities persistent.
         */

        @Deprecated
        public static final java.lang.String PERSISTENT_ACTIVITY = "android.permission.PERSISTENT_ACTIVITY";

        /**
         * Allows an application to see the number being dialed during an outgoing
         * call with the option to redirect the call to a different number or
         * abort the call altogether.
         * <p>Protection level: dangerous
         */

        public static final java.lang.String PROCESS_OUTGOING_CALLS = "android.permission.PROCESS_OUTGOING_CALLS";

        /**
         * Allows an application to read the user's calendar data.
         * <p>Protection level: dangerous
         */

        public static final java.lang.String READ_CALENDAR = "android.permission.READ_CALENDAR";

        /**
         * Allows an application to read the user's call log.
         * <p class="note"><strong>Note:</strong> If your app uses the
         * {@link #READ_CONTACTS} permission and <em>both</em> your <a
         * href="{@docRoot}guide/topics/manifest/uses-sdk-element.html#min">{@code
         * minSdkVersion}</a> and <a
         * href="{@docRoot}guide/topics/manifest/uses-sdk-element.html#target">{@code
         * targetSdkVersion}</a> values are set to 15 or lower, the system implicitly
         * grants your app this permission. If you don't need this permission, be sure your <a
         * href="{@docRoot}guide/topics/manifest/uses-sdk-element.html#target">{@code
         * targetSdkVersion}</a> is 16 or higher.</p>
         * <p>Protection level: dangerous
         */

        public static final java.lang.String READ_CALL_LOG = "android.permission.READ_CALL_LOG";

        /**
         * Allows an application to read the user's contacts data.
         * <p>Protection level: dangerous
         */

        public static final java.lang.String READ_CONTACTS = "android.permission.READ_CONTACTS";

        /**
         * Allows an application to read from external storage.
         * <p>Any app that declares the {@link #WRITE_EXTERNAL_STORAGE} permission is implicitly
         * granted this permission.</p>
         * <p>This permission is enforced starting in API level 19.  Before API level 19, this
         * permission is not enforced and all apps still have access to read from external storage.
         * You can test your app with the permission enforced by enabling <em>Protect USB
         * storage</em> under Developer options in the Settings app on a device running Android 4.1 or
         * higher.</p>
         * <p>Also starting in API level 19, this permission is <em>not</em> required to
         * read/write files in your application-specific directories returned by
         * {@link android.content.Context#getExternalFilesDir} and
         * {@link android.content.Context#getExternalCacheDir}.
         * <p class="note"><strong>Note:</strong> If <em>both</em> your <a
         * href="{@docRoot}guide/topics/manifest/uses-sdk-element.html#min">{@code
         * minSdkVersion}</a> and <a
         * href="{@docRoot}guide/topics/manifest/uses-sdk-element.html#target">{@code
         * targetSdkVersion}</a> values are set to 3 or lower, the system implicitly
         * grants your app this permission. If you don't need this permission, be sure your <a
         * href="{@docRoot}guide/topics/manifest/uses-sdk-element.html#target">{@code
         * targetSdkVersion}</a> is 4 or higher.
         * <p>Protection level: dangerous
         */

        public static final java.lang.String READ_EXTERNAL_STORAGE = "android.permission.READ_EXTERNAL_STORAGE";

        /**
         * Allows an application to take screen shots and more generally
         * get access to the frame buffer data.
         * <p>Not for use by third-party applications.
         */

        public static final java.lang.String READ_FRAME_BUFFER = "android.permission.READ_FRAME_BUFFER";

        /**
         * Allows an application to retrieve the current state of keys and
         * switches.
         * <p>Not for use by third-party applications.
         *
         * @deprecated The API that used this permission has been removed.
         */

        @Deprecated
        public static final java.lang.String READ_INPUT_STATE = "android.permission.READ_INPUT_STATE";

        /**
         * Allows an application to read the low-level system log files.
         * <p>Not for use by third-party applications, because
         * Log entries can contain the user's private information.
         */

        public static final java.lang.String READ_LOGS = "android.permission.READ_LOGS";

        /**
         * Allows read access to the device's phone number(s). This is a subset of the capabilities
         * granted by {@link #READ_PHONE_STATE} but is exposed to instant applications.
         * <p>Protection level: dangerous
         */

        public static final java.lang.String READ_PHONE_NUMBERS = "android.permission.READ_PHONE_NUMBERS";

        /**
         * Allows read only access to phone state, including the phone number of the device,
         * current cellular network information, the status of any ongoing calls, and a list of any
         * {@link android.telecom.PhoneAccount}s registered on the device.
         * <p class="note"><strong>Note:</strong> If <em>both</em> your <a
         * href="{@docRoot}guide/topics/manifest/uses-sdk-element.html#min">{@code
         * minSdkVersion}</a> and <a
         * href="{@docRoot}guide/topics/manifest/uses-sdk-element.html#target">{@code
         * targetSdkVersion}</a> values are set to 3 or lower, the system implicitly
         * grants your app this permission. If you don't need this permission, be sure your <a
         * href="{@docRoot}guide/topics/manifest/uses-sdk-element.html#target">{@code
         * targetSdkVersion}</a> is 4 or higher.
         * <p>Protection level: dangerous
         */

        public static final java.lang.String READ_PHONE_STATE = "android.permission.READ_PHONE_STATE";

        /**
         * Allows an application to read SMS messages.
         * <p>Protection level: dangerous
         */

        public static final java.lang.String READ_SMS = "android.permission.READ_SMS";

        /**
         * Allows applications to read the sync settings.
         * <p>Protection level: normal
         */

        public static final java.lang.String READ_SYNC_SETTINGS = "android.permission.READ_SYNC_SETTINGS";

        /**
         * Allows applications to read the sync stats.
         * <p>Protection level: normal
         */

        public static final java.lang.String READ_SYNC_STATS = "android.permission.READ_SYNC_STATS";

        /**
         * Allows an application to read voicemails in the system.
         * <p>Protection level: signature|privileged
         */

        public static final java.lang.String READ_VOICEMAIL = "com.android.voicemail.permission.READ_VOICEMAIL";

        /**
         * Required to be able to reboot the device.
         * <p>Not for use by third-party applications.
         */

        public static final java.lang.String REBOOT = "android.permission.REBOOT";

        /**
         * Allows an application to receive the
         * {@link android.content.Intent#ACTION_BOOT_COMPLETED} that is
         * broadcast after the system finishes booting.  If you don't
         * request this permission, you will not receive the broadcast at
         * that time.  Though holding this permission does not have any
         * security implications, it can have a negative impact on the
         * user experience by increasing the amount of time it takes the
         * system to start and allowing applications to have themselves
         * running without the user being aware of them.  As such, you must
         * explicitly declare your use of this facility to make that visible
         * to the user.
         * <p>Protection level: normal
         */

        public static final java.lang.String RECEIVE_BOOT_COMPLETED = "android.permission.RECEIVE_BOOT_COMPLETED";

        /**
         * Allows an application to monitor incoming MMS messages.
         * <p>Protection level: dangerous
         */

        public static final java.lang.String RECEIVE_MMS = "android.permission.RECEIVE_MMS";

        /**
         * Allows an application to receive SMS messages.
         * <p>Protection level: dangerous
         */

        public static final java.lang.String RECEIVE_SMS = "android.permission.RECEIVE_SMS";

        /**
         * Allows an application to receive WAP push messages.
         * <p>Protection level: dangerous
         */

        public static final java.lang.String RECEIVE_WAP_PUSH = "android.permission.RECEIVE_WAP_PUSH";

        /**
         * Allows an application to record audio.
         * <p>Protection level: dangerous
         */

        public static final java.lang.String RECORD_AUDIO = "android.permission.RECORD_AUDIO";

        /**
         * Allows an application to change the Z-order of tasks.
         * <p>Protection level: normal
         */

        public static final java.lang.String REORDER_TASKS = "android.permission.REORDER_TASKS";

        /**
         * Allows a companion app to run in the background.
         * <p>Protection level: normal
         */

        public static final java.lang.String REQUEST_COMPANION_RUN_IN_BACKGROUND = "android.permission.REQUEST_COMPANION_RUN_IN_BACKGROUND";

        /**
         * Allows a companion app to use data in the background.
         * <p>Protection level: normal
         */

        public static final java.lang.String REQUEST_COMPANION_USE_DATA_IN_BACKGROUND = "android.permission.REQUEST_COMPANION_USE_DATA_IN_BACKGROUND";

        /**
         * Allows an application to request deleting packages. Apps
         * targeting APIs {@link android.os.Build.VERSION_CODES#P} or greater must hold this
         * permission in order to use {@link android.content.Intent#ACTION_UNINSTALL_PACKAGE} or
         * {@link android.content.pm.PackageInstaller#uninstall}.
         * <p>Protection level: normal
         */

        public static final java.lang.String REQUEST_DELETE_PACKAGES = "android.permission.REQUEST_DELETE_PACKAGES";

        /**
         * Permission an application must hold in order to use
         * {@link android.provider.Settings#ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS}.
         * This is a normal permission: an app requesting it will always be granted the
         * permission, without the user needing to approve or see it.
         */

        public static final java.lang.String REQUEST_IGNORE_BATTERY_OPTIMIZATIONS = "android.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS";

        /**
         * Allows an application to request installing packages. Apps
         * targeting APIs greater than 25 must hold this permission in
         * order to use {@link android.content.Intent#ACTION_INSTALL_PACKAGE}.
         * <p>Protection level: signature
         */

        public static final java.lang.String REQUEST_INSTALL_PACKAGES = "android.permission.REQUEST_INSTALL_PACKAGES";

        /**
         * @deprecated The {@link android.app.ActivityManager#restartPackage}
         * API is no longer supported.
         */

        @Deprecated
        public static final java.lang.String RESTART_PACKAGES = "android.permission.RESTART_PACKAGES";

        /**
         * Allows an application (Phone) to send a request to other applications
         * to handle the respond-via-message action during incoming calls.
         * <p>Not for use by third-party applications.
         */

        public static final java.lang.String SEND_RESPOND_VIA_MESSAGE = "android.permission.SEND_RESPOND_VIA_MESSAGE";

        /**
         * Allows an application to send SMS messages.
         * <p>Protection level: dangerous
         */

        public static final java.lang.String SEND_SMS = "android.permission.SEND_SMS";

        /**
         * Allows an application to broadcast an Intent to set an alarm for the user.
         * <p>Protection level: normal
         */

        public static final java.lang.String SET_ALARM = "com.android.alarm.permission.SET_ALARM";

        /**
         * Allows an application to control whether activities are immediately
         * finished when put in the background.
         * <p>Not for use by third-party applications.
         */

        public static final java.lang.String SET_ALWAYS_FINISH = "android.permission.SET_ALWAYS_FINISH";

        /**
         * Modify the global animation scaling factor.
         * <p>Not for use by third-party applications.
         */

        public static final java.lang.String SET_ANIMATION_SCALE = "android.permission.SET_ANIMATION_SCALE";

        /**
         * Configure an application for debugging.
         * <p>Not for use by third-party applications.
         */

        public static final java.lang.String SET_DEBUG_APP = "android.permission.SET_DEBUG_APP";

        /**
         * @deprecated No longer useful, see
         * {@link android.content.pm.PackageManager#addPackageToPreferred}
         * for details.
         */

        @Deprecated
        public static final java.lang.String SET_PREFERRED_APPLICATIONS = "android.permission.SET_PREFERRED_APPLICATIONS";

        /**
         * Allows an application to set the maximum number of (not needed)
         * application processes that can be running.
         * <p>Not for use by third-party applications.
         */

        public static final java.lang.String SET_PROCESS_LIMIT = "android.permission.SET_PROCESS_LIMIT";

        /**
         * Allows applications to set the system time.
         * <p>Not for use by third-party applications.
         */

        public static final java.lang.String SET_TIME = "android.permission.SET_TIME";

        /**
         * Allows applications to set the system time zone.
         * <p>Not for use by third-party applications.
         */

        public static final java.lang.String SET_TIME_ZONE = "android.permission.SET_TIME_ZONE";

        /**
         * Allows applications to set the wallpaper.
         * <p>Protection level: normal
         */

        public static final java.lang.String SET_WALLPAPER = "android.permission.SET_WALLPAPER";

        /**
         * Allows applications to set the wallpaper hints.
         * <p>Protection level: normal
         */

        public static final java.lang.String SET_WALLPAPER_HINTS = "android.permission.SET_WALLPAPER_HINTS";

        /**
         * Allow an application to request that a signal be sent to all persistent processes.
         * <p>Not for use by third-party applications.
         */

        public static final java.lang.String SIGNAL_PERSISTENT_PROCESSES = "android.permission.SIGNAL_PERSISTENT_PROCESSES";

        /**
         * Allows an application to open, close, or disable the status bar
         * and its icons.
         * <p>Not for use by third-party applications.
         */

        public static final java.lang.String STATUS_BAR = "android.permission.STATUS_BAR";

        /**
         * Allows an app to create windows using the type
         * {@link android.view.WindowManager.LayoutParams#TYPE_APPLICATION_OVERLAY},
         * shown on top of all other apps.  Very few apps
         * should use this permission; these windows are intended for
         * system-level interaction with the user.
         * <p class="note"><strong>Note:</strong> If the app
         * targets API level 23 or higher, the app user must explicitly grant
         * this permission to the app through a permission management screen. The app requests
         * the user's approval by sending an intent with action
         * {@link android.provider.Settings#ACTION_MANAGE_OVERLAY_PERMISSION}.
         * The app can check whether it has this authorization by calling
         * {@link android.provider.Settings#canDrawOverlays
         * Settings.canDrawOverlays()}.
         * <p>Protection level: signature
         */

        public static final java.lang.String SYSTEM_ALERT_WINDOW = "android.permission.SYSTEM_ALERT_WINDOW";

        /**
         * Allows using the device's IR transmitter, if available.
         * <p>Protection level: normal
         */

        public static final java.lang.String TRANSMIT_IR = "android.permission.TRANSMIT_IR";

        /**
         * <p class="caution"><strong>Don't use this permission in your app.</strong><br>This
         * permission is no longer supported.
         */

        public static final java.lang.String UNINSTALL_SHORTCUT = "com.android.launcher.permission.UNINSTALL_SHORTCUT";

        /**
         * Allows an application to update device statistics.
         * <p>Not for use by third-party applications.
         */

        public static final java.lang.String UPDATE_DEVICE_STATS = "android.permission.UPDATE_DEVICE_STATS";

        /**
         * Allows an app to use device supported biometric modalities.
         * <p>Protection level: normal
         */

        public static final java.lang.String USE_BIOMETRIC = "android.permission.USE_BIOMETRIC";

        /**
         * Allows an app to use fingerprint hardware.
         * <p>Protection level: normal
         *
         * @deprecated Applications should request {@link
         * android.Manifest.permission#USE_BIOMETRIC} instead
         */

        @Deprecated
        public static final java.lang.String USE_FINGERPRINT = "android.permission.USE_FINGERPRINT";

        /**
         * Allows an application to use SIP service.
         * <p>Protection level: dangerous
         */

        public static final java.lang.String USE_SIP = "android.permission.USE_SIP";

        /**
         * Allows access to the vibrator.
         * <p>Protection level: normal
         */

        public static final java.lang.String VIBRATE = "android.permission.VIBRATE";

        /**
         * Allows using PowerManager WakeLocks to keep processor from sleeping or screen
         * from dimming.
         * <p>Protection level: normal
         */

        public static final java.lang.String WAKE_LOCK = "android.permission.WAKE_LOCK";

        /**
         * Allows applications to write the apn settings.
         * <p>Not for use by third-party applications.
         */

        public static final java.lang.String WRITE_APN_SETTINGS = "android.permission.WRITE_APN_SETTINGS";

        /**
         * Allows an application to write the user's calendar data.
         * <p>Protection level: dangerous
         */

        public static final java.lang.String WRITE_CALENDAR = "android.permission.WRITE_CALENDAR";

        /**
         * Allows an application to write (but not read) the user's
         * call log data.
         * <p class="note"><strong>Note:</strong> If your app uses the
         * {@link #WRITE_CONTACTS} permission and <em>both</em> your <a
         * href="{@docRoot}guide/topics/manifest/uses-sdk-element.html#min">{@code
         * minSdkVersion}</a> and <a
         * href="{@docRoot}guide/topics/manifest/uses-sdk-element.html#target">{@code
         * targetSdkVersion}</a> values are set to 15 or lower, the system implicitly
         * grants your app this permission. If you don't need this permission, be sure your <a
         * href="{@docRoot}guide/topics/manifest/uses-sdk-element.html#target">{@code
         * targetSdkVersion}</a> is 16 or higher.</p>
         * <p>Protection level: dangerous
         */

        public static final java.lang.String WRITE_CALL_LOG = "android.permission.WRITE_CALL_LOG";

        /**
         * Allows an application to write the user's contacts data.
         * <p>Protection level: dangerous
         */

        public static final java.lang.String WRITE_CONTACTS = "android.permission.WRITE_CONTACTS";

        /**
         * Allows an application to write to external storage.
         * <p class="note"><strong>Note:</strong> If <em>both</em> your <a
         * href="{@docRoot}guide/topics/manifest/uses-sdk-element.html#min">{@code
         * minSdkVersion}</a> and <a
         * href="{@docRoot}guide/topics/manifest/uses-sdk-element.html#target">{@code
         * targetSdkVersion}</a> values are set to 3 or lower, the system implicitly
         * grants your app this permission. If you don't need this permission, be sure your <a
         * href="{@docRoot}guide/topics/manifest/uses-sdk-element.html#target">{@code
         * targetSdkVersion}</a> is 4 or higher.
         * <p>Starting in API level 19, this permission is <em>not</em> required to
         * read/write files in your application-specific directories returned by
         * {@link android.content.Context#getExternalFilesDir} and
         * {@link android.content.Context#getExternalCacheDir}.
         * <p>Protection level: dangerous
         */

        public static final java.lang.String WRITE_EXTERNAL_STORAGE = "android.permission.WRITE_EXTERNAL_STORAGE";

        /**
         * Allows an application to modify the Google service map.
         * <p>Not for use by third-party applications.
         */

        public static final java.lang.String WRITE_GSERVICES = "android.permission.WRITE_GSERVICES";

        /**
         * Allows an application to read or write the secure system settings.
         * <p>Not for use by third-party applications.
         */

        public static final java.lang.String WRITE_SECURE_SETTINGS = "android.permission.WRITE_SECURE_SETTINGS";

        /**
         * Allows an application to read or write the system settings.
         * <p class="note"><strong>Note:</strong> If the app targets API level 23
         * or higher, the app user
         * must explicitly grant this permission to the app through a permission management screen.
         * The app requests the user's approval by sending an intent with action
         * {@link android.provider.Settings#ACTION_MANAGE_WRITE_SETTINGS}. The app
         * can check whether it has this authorization by calling {@link
         * android.provider.Settings.System#canWrite Settings.System.canWrite()}.
         * <p>Protection level: signature
         */

        public static final java.lang.String WRITE_SETTINGS = "android.permission.WRITE_SETTINGS";

        /**
         * Allows applications to write the sync settings.
         * <p>Protection level: normal
         */

        public static final java.lang.String WRITE_SYNC_SETTINGS = "android.permission.WRITE_SYNC_SETTINGS";

        /**
         * Allows an application to modify and remove existing voicemails in the system.
         * <p>Protection level: signature|privileged
         */

        public static final java.lang.String WRITE_VOICEMAIL = "com.android.voicemail.permission.WRITE_VOICEMAIL";
    }

    @SuppressWarnings({"unchecked", "deprecation", "all"})
    public static final class permission_group {

        public permission_group() {
            throw new RuntimeException("Stub!");
        }

        /**
         * Used for runtime permissions related to user's calendar.
         */

        public static final java.lang.String CALENDAR = "android.permission-group.CALENDAR";

        /**
         * Used for permissions that are associated telephony features.
         */

        public static final java.lang.String CALL_LOG = "android.permission-group.CALL_LOG";

        /**
         * Used for permissions that are associated with accessing
         * camera or capturing images/video from the device.
         */

        public static final java.lang.String CAMERA = "android.permission-group.CAMERA";

        /**
         * Used for runtime permissions related to contacts and profiles on this
         * device.
         */

        public static final java.lang.String CONTACTS = "android.permission-group.CONTACTS";

        /**
         * Used for permissions that allow accessing the device location.
         */

        public static final java.lang.String LOCATION = "android.permission-group.LOCATION";

        /**
         * Used for permissions that are associated with accessing
         * microphone audio from the device. Note that phone calls also capture audio
         * but are in a separate (more visible) permission group.
         */

        public static final java.lang.String MICROPHONE = "android.permission-group.MICROPHONE";

        /**
         * Used for permissions that are associated telephony features.
         */

        public static final java.lang.String PHONE = "android.permission-group.PHONE";

        /**
         * Used for permissions that are associated with accessing
         * body or environmental sensors.
         */

        public static final java.lang.String SENSORS = "android.permission-group.SENSORS";

        /**
         * Used for runtime permissions related to user's SMS messages.
         */

        public static final java.lang.String SMS = "android.permission-group.SMS";

        /**
         * Used for runtime permissions related to the shared external storage.
         */

        public static final java.lang.String STORAGE = "android.permission-group.STORAGE";
    }

}
