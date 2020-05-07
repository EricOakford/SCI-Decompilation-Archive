;;; Sierra Script 1.0 - (do not remove this comment)
; * SCI Menu Header
;
; * Put all the defines specific to your game's menu bar in here

;NOTE: SSCI allowed defining multiple "starting" numbers in a single enum, but SCICompanion only allows once
; so, we have to start multiple enums to define the entirety of the menu system.
(enum
$100 sierraM
	aboutI
)
(enum
$200 actionM
	restartI
	quitI
)
(enum
$300 soundM
	volumeI
	soundI
)

(enum
$400 debugM
	areaI
)
