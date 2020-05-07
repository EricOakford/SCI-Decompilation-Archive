;;; Sierra Script 1.0 - (do not remove this comment)
;NOTE: SSCI allowed defining multiple "starting" numbers in a single enum, but SCICompanion only allows once
; so, we have to start multiple enums to define the entirety of the menu system.
(enum
$100 sierraM
	aboutI
	helpI
)
(enum
$200 fileM
	saveI
	restoreI
		divider201I
	restartI
	quitI
)
(enum
$300 gameM
	fasterI
	normalI
	slowerI
		divider301I	
	volumeI
	soundI
		divider302I
;	highSpeedHeroI
)
(enum
$400 actionM
	castI
	fightI
	escapeI
		divider401I
	pauseI
	repeatI
;	bossI
;	triteI
)
(enum
$500 infoM
	invI
	charI
		divider501I
	timeI
	askI
	lookI
)
;(enum
;$600 debugM
;	gameI
;	castI
;	egoI
;	tglDebugI
;	memI
;		divider601I
;	visualI
;	priorityI
;	controlI
;		divider602I
;	gridI
;	writeEgoI
;)
;(enum
;$700 cheatM
;	teleportI
;	rgTimeI
;	clothingI
;	roomI
;	inputI
;	noteI
;	quickQuitI
;)
