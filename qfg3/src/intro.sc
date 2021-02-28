;;; Sierra Script 1.0 - (do not remove this comment)
(script# INTRO)
(include game.sh) (include "61.shm")
(use Main)
(use GloryWindow)
(use IconBar)
(use GControl)
(use Game)
(use System)

(public
	intro 0
)

(local
	[yesBuf 10]
	[noBuf 10]
	printRet
)
(procedure (ShowPrompt)
	(Message MsgGet INTRO N_INTRO V_DOIT C_YES 1 @yesBuf)
	(Message MsgGet INTRO N_INTRO V_DOIT C_NO 1 @noBuf)
	(quest init: show: dispose:)
	(return printRet)
)

(instance intro of Region
	
	(method (init)
		(mouseDownHandler add: self)
		(keyDownHandler add: self)
		(super init: &rest)
	)
	
	(method (handleEvent event)
		(if (IsObject fastCast)
			(super handleEvent: event &rest)
		else
			(cond 
				((event claimed?) FALSE)
				((OneOf (event type?) keyDown mouseDown joyDown)
					(event claimed: TRUE)
					(sounds eachElementDo: #pause 1)
					(if (ShowPrompt)
						(sounds eachElementDo: #pause 0)
						(curRoom newRoom: 280)
					)
					(sounds eachElementDo: #pause 0)
				)
				((== (event type?) mouseUp)
					(event claimed: TRUE)
				)
			)
			(super handleEvent: event &rest)
		)
	)
)

(instance quest of GameControls

	(method (init)
		(theGame setCursor: ARROW_CURSOR)
		((= window (GloryWindow new:))
			top: 60
			left: 97
			bottom: 115
			right: 223
			priority: 15
			yourself:
		)
		(self add: titleIcon yesIcon noIcon)
		(super init: &rest)
	)
)

(instance titleIcon of IconItem
	(properties
		view 935
		loop 2
		cel 0
		nsTop 0
		signal DISABLED
		maskView 361
		maskLoop 3
	)
	
	(method (show &tmp [str 30])
		(Message MsgGet INTRO N_INTRO V_DOIT C_ASK_SKIP 1 @str)
		(Display @str
			p_width 135
			p_at 5 3
			p_color 17
		)
	)
)

(instance yesIcon of IconItem
	(properties
		view 935
		loop 2
		cel 0
		nsTop 25
		signal (| VICON RELVERIFY)
		maskView 361
		maskLoop 3
	)
	
	(method (show)
		(= nsRight 80)
		(= nsBottom (+ nsTop 15))
		(DrawCel view loop cel nsLeft nsTop -1)
		(Display @yesBuf
			p_at 20 (+ nsTop 3)
			p_color 17
		)
		(if (& signal DISABLED)
			(self mask:)
		)
		(if (and pMouse (pMouse respondsTo: #stop))
			(pMouse stop:)
		)
	)
	
	(method (select)
		(= printRet 1)
		(quest state: (& (quest state?) $ffdf))
	)
	
	(method (highlight tOrF &tmp sColor)
		(if tOrF
			(DrawCel view loop 1 nsLeft nsTop -1)
			(= sColor 46)
		else
			(DrawCel view loop 0 nsLeft nsTop -1)
			(= sColor 17)
		)
		(Display @yesBuf
			p_at 20 (+ nsTop 3)
			p_color sColor
		)
	)
)

(instance noIcon of IconItem
	(properties
		view 935
		loop 2
		cel 0
		nsTop 40
		signal (| VICON RELVERIFY)
		maskView 361
		maskLoop 3
	)
	
	(method (show)
		(= nsRight 80)
		(= nsBottom (+ nsTop 15))
		(DrawCel view loop cel nsLeft nsTop -1)
		(Display @noBuf p_at 20 (+ nsTop 3) p_color 17)
		(if (& signal DISABLED)
			(self mask:)
		)
		(if (and pMouse (pMouse respondsTo: #stop))
			(pMouse stop:)
		)
	)
	
	(method (select)
		(= printRet 0)
		(quest state: (& (quest state?) $ffdf))
	)
	
	(method (highlight tOrF &tmp sColor)
		(if tOrF
			(DrawCel view loop 1 nsLeft nsTop -1)
			(= sColor 46)
		else
			(DrawCel view loop 0 nsLeft nsTop -1)
			(= sColor 17)
		)
		(Display @noBuf p_at 20 (+ nsTop 3) p_color sColor)
	)
)
