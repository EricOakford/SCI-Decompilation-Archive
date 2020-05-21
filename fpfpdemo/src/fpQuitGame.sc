;;; Sierra Script 1.0 - (do not remove this comment)
(script# FPQUIT) ;31
(include game.sh)
(use Main)
(use GControl)
(use Window)

(public
	fpQuitGameControls 0
	quitWin 1
)

(class QuitControlIcon of ControlIcon
	(properties)
	
	(method (highlight param1 &tmp temp0)
		(if (or (not (& signal $0020)) (== highlightColor -1))
			(return)
		)
		(if (and argc param1) (= temp0 3) else (= temp0 0))
		(DrawCel view loop temp0 nsLeft nsTop 15)
	)
)

(instance fpQuitGameControls of GameControls
	(properties)
	
	(method (init)
		(= gameControls self)
		(self
			add: iconPlay iconQuit
			eachElementDo: #highlightColor 40
			eachElementDo: #lowlightColor 0
			curIcon: iconPlay
			window: quitWin
		)
		(super init: &rest)
	)
	
	(method (dispose)
		(super dispose:)
		(theGame setCursor: normalCursor)
		(DisposeScript GCONTROL)
		(DisposeScript FPQUIT)
	)
	
	(method (show)
		(theGame setCursor: HAND_CURSOR)
		(super show: &rest)
	)
)

(instance quitWin of SysWindow
	(properties)
	
	(method (open &tmp port [temp1 4] temp5 [temp6 20])
		(= type $80)
		(= left 48)
		(= top 24)
		(= right 272)
		(= bottom 176)
		(= lsLeft left)
		(= lsTop top)
		(= lsRight right)
		(= lsBottom bottom)
		(= priority 15)
		(super open:)
		(= port (GetPort))
		(SetPort 0)
		(DrawCel 992 0 0 48 24 temp5)
		(Graph GShowBits lsTop lsLeft lsBottom lsRight VMAP)
		(theGame setCursor: ARROW_CURSOR)
		(SetPort port)
	)
)

(instance iconQuit of QuitControlIcon
	(properties
		view 992
		loop 1
		cel 0
		nsLeft 184
		nsTop 59
		cursor 9
		message V_EXIT
		signal (| RELVERIFY IMMEDIATE FIXED_POSN VICON HIDEBAR)
		modNum FPQUIT
		helpVerb V_HELP
	)
	
	(method (select)
		(= quit TRUE)
	)
)

(instance iconPlay of QuitControlIcon
	(properties
		view 992
		loop 2
		cel 0
		nsLeft 184
		nsTop 99
		cursor 9
		message V_EXIT
		signal (| RELVERIFY IMMEDIATE FIXED_POSN VICON HIDEBAR)
		modNum FPQUIT
		helpVerb V_HELP
	)
)
