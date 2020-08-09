;;; Sierra Script 1.0 - (do not remove this comment)
(script# FPQUIT)
(include game.sh)
(use Main)
(use GControl)
(use Window)

(public
	fpQuitGameControls 0
	quitWin 1
)

(class QuitControlIcon of ControlIcon
	
	(method (highlight theIcon &tmp theCel)
		(if (or (not (& signal IB_ACTIVE)) (== highlightColor -1))
			(return)
		)
		(if (and argc theIcon)
			(= theCel 3)
		else
			(= theCel 0)
		)
		(DrawCel view loop theCel nsLeft nsTop 15)
	)
)

(instance fpQuitGameControls of GameControls
	
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
	
	(method (open &tmp savePort t l b r temp5 [str 20])
		(= type 128)
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
		(= savePort (GetPort))
		(SetPort 0)
		(DrawCel 992 0 0 48 24 temp5)
		(Graph GShowBits lsTop lsLeft lsBottom lsRight VMAP)
		(theGame setCursor: ARROW_CURSOR)
		(SetPort savePort)
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
		message V_GAME
		signal (| VICON HIDEBAR FIXED_POSN RELVERIFY IMMEDIATE)
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
		message V_GAME
		signal (| VICON HIDEBAR FIXED_POSN RELVERIFY IMMEDIATE)
		modNum FPQUIT
		helpVerb V_HELP
	)
)
