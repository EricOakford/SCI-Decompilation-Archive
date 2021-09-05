;;; Sierra Script 1.0 - (do not remove this comment)
(script# 705)
(include game.sh)
(use Main)
(use GloryWindow)
(use IconBar)
(use GControl)
(use System)

(public
	askFire 0
)

(local
	fireControls
)
(instance askFire of Code
	
	(method (init n v c &tmp obj)
		((= fireControls (GameControls new:))
			window:
				((GloryWindow new:)
					top: 40
					left: 65
					bottom: 80
					right: 256
					priority: 15
					yourself:
				)
		)
		((= obj (fireIcon new: n v c 1))
			view: 935
			loop: 1
			cel: 0
			nsTop: 2
			nsLeft: 2
			modifiers: teJustCenter
		)
		(fireControls add: obj)
		((= obj (fireIcon new: n v c 2))
			nsTop: 25
			nsLeft: 5
			cursor: 1
		)
		(fireControls add: obj)
		((= obj (fireIcon new: n v c 3))
			nsTop: 25
			nsLeft: 95
			cursor: 2
		)
		(fireControls add: obj)
		(fireControls init: show: dispose:)
		(= fireControls 0)
	)
)

(instance fireIcon of IconItem
	(properties
		view 935
		loop 2
		cel 0
	)
	
	(method (new theNoun theVerb theCase theSeq &tmp obj s temp2)
		(= obj (Clone self))
		(if argc
			(= s
				(Message MsgSize curRoomNum theNoun theVerb theCase theSeq)
			)
			(obj message: (Memory MNewPtr s))
			(Message MsgGet curRoomNum theNoun theVerb theCase theSeq (obj message?))
		)
		(return obj)
	)
	
	(method (dispose)
		(Memory MDisposePtr message)
		(super dispose:)
	)
	
	(method (show)
		(= nsRight (+ nsLeft (if (== loop 1) 0 else 90)))
		(= nsBottom (if (== loop 1) nsTop else (+ nsTop 15)))
		(DrawCel view loop cel nsLeft nsTop -1)
		(Display message
			p_at (if (== loop 1) nsLeft else (+ nsLeft 20)) (+ nsTop 2)
			p_font (if (== loop 1) 123 else 0)
			p_color 17
			p_mode modifiers
			p_width (if (== loop 1) 189 else 70)
		)
	)
	
	(method (select)
		(return
			(if (!= loop 1)
				(= controlRet cursor)
				(fireControls
					state: (& (fireControls state?) $ffdf)
				)
			else
				(return FALSE)
			)
		)
	)
	
	(method (highlight tOrF &tmp sColor)
		(if (!= loop 1)
			(if tOrF
				(DrawCel view loop 1 nsLeft nsTop -1)
				(= sColor 46)
			else
				(DrawCel view loop 0 nsLeft nsTop -1)
				(= sColor 17)
			)
			(Display message
				p_at (if (== loop 1) nsLeft else (+ nsLeft 20)) (+ nsTop 2)
				p_font (if (== loop 1) 123 else 0)
				p_color sColor
				p_mode modifiers
				p_width (if (== loop 1) 189 else 70)
			)
		)
	)
)
