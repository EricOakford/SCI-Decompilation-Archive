;;; Sierra Script 1.0 - (do not remove this comment)
(script# 890)
(include game.sh) (include "26.shm")
(use Main)
(use GloryWindow)
(use IconBar)
(use GControl)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm890 0
)

(local
	printRet
	[contBuf 10]
	[restoreBuf 10]
	[restartBuf 10]
	[quitBuf 5]
)
(procedure (EndPrint)
	(Message MsgGet EGODEAD N_DEATH NULL C_CONTINUE 1 @contBuf)
	(Message MsgGet EGODEAD N_DEATH NULL C_RESTORE 1 @restoreBuf)
	(Message MsgGet EGODEAD N_DEATH NULL C_RESTART 1 @restartBuf)
	(Message MsgGet EGODEAD N_DEATH NULL C_QUIT 1 @quitBuf)
	(quest init: show: dispose:)
	(switch printRet
		(1
			(theGame setCursor: INVIS_CURSOR TRUE)
			(SetCursor 0)
		)
		(2
			(theGame restart:)
			(theGame setCursor: INVIS_CURSOR TRUE)
			(SetCursor 0)
		)
		(3
			(theGame restore:)
			(theGame setCursor: INVIS_CURSOR TRUE)
			(SetCursor 0)
		)
		(4
			(= quit TRUE)
		)
	)
)

(instance rm890 of Room
	
	(method (init)
		(super init:)
		(theGame setScript: weBeFamous)
	)
)

(instance weBeFamous of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(theIconBar disable:)
				(theGame setCursor: INVIS_CURSOR TRUE)
				(SetCursor 0)
				(cSound number: 231 setLoop: -1 play: 127)
				(Graph GFillRect 0 0 200 320 1 22 -1)
				(Graph GShowBits -1 -1 201 321 1)
				(iconProp view: 919 x: 10 y: 110 cel: 0 init:)
				(signActor
					view: 920
					x: 152
					y: 220
					setLoop: 0
					cel: 0
					init:
					setMotion: MoveTo 152 86 self
				)
			)
			(1
				(= ticks 240)
			)
			(2
				(signActor setCel: 1)
				(= ticks 300)
			)
			(3
				(signActor setMotion: MoveTo 360 86 self)
			)
			(4
				(signActor x: -20 setCel: 2 setMotion: MoveTo 152 86 self)
			)
			(5
				(iconProp setCycle: EndLoop self)
				(globalSound number: 831 setLoop: 1 play:)
			)
			(6 (= ticks 240))
			(7
				(signActor setMotion: MoveTo 152 -20 self)
			)
			(8
				(signActor
					setLoop: 2
					cel: 0
					setMotion: MoveTo 152 86 self
				)
			)
			(9 (= ticks 180))
			(10
				(signActor setCel: 1)
				(= ticks 240)
			)
			(11
				(signActor setMotion: MoveTo 152 250 self)
			)
			(12
				(iconProp view: 912 x: 233 y: 112)
				(signActor
					y: -20
					setLoop: 4
					setCel: 0
					setMotion: MoveTo 152 86 self
				)
			)
			(13
				(iconProp setCycle: EndLoop self)
				(globalSound number: 101 setLoop: 1 play:)
			)
			(14
				(globalSound number: 929 setLoop: 1 play:)
				(signActor setCel: 1)
				(= ticks 300)
			)
			(15
				(signActor setMotion: MoveTo 152 220 self)
			)
			(16
				(signActor
					setLoop: 6
					cel: 0
					setMotion: MoveTo 152 86 self
				)
				(iconProp view: 913 x: 0 cel: 0)
			)
			(17 (= ticks 180))
			(18
				(iconProp setCycle: EndLoop)
				(signActor cel: 1)
				(globalSound number: 940 setLoop: 1 play: self)
			)
			(19
				(globalSound number: 891 setLoop: 1 play:)
				(= ticks 300)
			)
			(20
				(signActor setMotion: MoveTo 330 210 self)
			)
			(21
				(iconProp view: 917 x: 253 y: 20 cel: 0)
				(signActor
					setLoop: 8
					cel: 0
					x: -20
					y: -20
					setMotion: MoveTo 152 86 self
				)
			)
			(22 (= ticks 120))
			(23
				(signActor setCel: 1)
				(iconProp cycleSpeed: 12 setCycle: EndLoop self)
				(globalSound number: 919 setLoop: -1 play:)
			)
			(24
				(globalSound number: 931 setLoop: 1 play:)
				(= ticks 120)
			)
			(25
				(signActor setMotion: MoveTo 152 220 self)
			)
			(26
				(iconProp view: 914 x: 0 y: 0 setCycle: Forward)
				(signActor
					setLoop: 10
					cel: 0
					x: 360
					y: 86
					setMotion: MoveTo 152 86 self
				)
				(cSound number: 890 setLoop: 1 play:)
			)
			(27 (= ticks 240))
			(28
				(signActor setCel: 1)
				(= ticks 240)
			)
			(29
				(signActor setMotion: MoveTo 152 220 self)
			)
			(30
				(cSound stop:)
				(signActor
					setLoop: 12
					y: -20
					cel: 0
					setMotion: MoveTo 152 86 self
				)
				(iconProp view: 909 x: 230 y: 95 cel: 0 setCycle: 0)
			)
			(31 (= ticks 180))
			(32
				(signActor setCel: 1)
				(iconProp setCycle: EndLoop self)
				(globalSound number: 291 setLoop: 1 play: self)
			)
			(33)
			(34
				(cSound number: 407 setLoop: -1 play:)
				(signActor setMotion: MoveTo 152 -20 self)
			)
			(35
				(signActor
					view: 921
					setLoop: 2
					setCel: 0
					setMotion: MoveTo 152 92 self
				)
				(iconProp view: 910 x: 76 y: 184 cel: 0)
			)
			(36 (= ticks 120))
			(37
				(iconProp setCycle: CycleTo 8 1 self)
				(signActor y: 120 setCel: 1)
			)
			(38 (= ticks 120))
			(39
				(globalSound number: 905 setLoop: 1 play:)
				(iconProp setCycle: EndLoop self)
			)
			(40 (= ticks 300))
			(41
				(signActor setCel: 2)
				(= ticks 300)
			)
			(42
				(signActor setMotion: MoveTo 152 250 self)
			)
			(43
				(iconProp view: 911 cel: 0 x: 189 y: 98)
				(signActor
					setLoop: 0
					cel: 0
					setMotion: MoveTo 152 86 self
				)
			)
			(44 (= ticks 120))
			(45
				(iconProp setCycle: CycleTo 3 1 self)
			)
			(46
				(globalSound number: 917 setLoop: 1 play: self)
			)
			(47
				(iconProp setCycle: CycleTo 6 1 self)
			)
			(48
				(globalSound number: 917 setLoop: 1 play: self)
			)
			(49
				(iconProp setCycle: EndLoop self)
			)
			(50
				(signActor x: 152 y: 131 setCel: 1)
				(= ticks 300)
			)
			(51
				(signActor setCel: 2)
				(= ticks 300)
			)
			(52
				(signActor setMotion: MoveTo 152 -40 self)
			)
			(53
				(iconProp view: 916 cel: 0 x: 259 y: 2)
				(signActor
					setLoop: 4
					x: -20
					y: -20
					setMotion: MoveTo 152 86 self
				)
				(cSound changeTo: 408)
			)
			(54 (= ticks 300))
			(55
				(signActor setCel: 1)
				(= ticks 180)
			)
			(56
				(signActor setMotion: MoveTo -20 220 self)
			)
			(57
				(signActor
					view: 922
					setLoop: 0
					cel: 0
					setMotion: MoveTo 152 86 self
				)
			)
			(58
				(iconProp setCycle: EndLoop self)
				(globalSound number: 850 setLoop: 1 play:)
			)
			(59
				(globalSound number: 912 setLoop: 1 play:)
				(= ticks 120)
			)
			(60
				(signActor y: 121 setCel: 1)
				(= ticks 300)
			)
			(61
				(signActor setCel: 2)
				(= ticks 300)
			)
			(62
				(signActor setCel: 3)
				(= ticks 300)
			)
			(63
				(signActor setMotion: MoveTo 152 -40 self)
			)
			(64
				(signActor
					setLoop: 2
					x: 360
					y: 220
					cel: 0
					setMotion: MoveTo 152 86 self
				)
				(iconProp view: 918 cel: 0 setCycle: Forward)
			)
			(65 (= ticks 180))
			(66
				(signActor setLoop: 3 y: 121 setCel: 2)
				(= ticks 180)
			)
			(67
				(signActor setLoop: 2)
				(iconProp setCycle: 0 loop: 1)
				(globalSound number: 403 setLoop: 1 play:)
				(= ticks 240)
				(cSound changeTo: 409)
			)
			(68
				(signActor view: 922 loop: 6 cel: 0 y: 86)
				(= ticks 180)
			)
			(69
				(signActor cel: 1 y: 121)
				(= ticks 90)
			)
			(70
				(signActor cel: 2)
				(= ticks 90)
			)
			(71
				(signActor setMotion: MoveTo 152 -20 self)
			)
			(72
				(iconProp dispose:)
				(signActor
					setLoop: 4
					cel: 0
					setMotion: MoveTo 152 86 self
				)
			)
			(73 (= ticks 240))
			(74
				(signActor setCel: 1)
				(= ticks 240)
			)
			(75
				(signActor setMotion: MoveTo -20 -20 self)
			)
			(76
				(signActor
					view: 923
					x: 340
					y: 220
					setLoop: 0
					cel: 0
					setMotion: MoveTo 152 86 self
				)
			)
			(77 (= ticks 240))
			(78
				(signActor setCel: 1)
				(= ticks 240)
			)
			(79
				(signActor setMotion: MoveTo 152 220 self)
			)
			(80
				(signActor
					setLoop: 2
					cel: 0
					setMotion: MoveTo 152 86 self
				)
			)
			(81 (= ticks 240))
			(82
				(signActor y: 131 setCel: 1)
				(= ticks 240)
			)
			(83
				(signActor setCel: 2)
				(= ticks 240)
			)
			(84
				(signActor setMotion: MoveTo 152 -20 self)
			)
			(85
				(iconProp view: 915 x: 92 y: 130 cel: 0 init:)
				(signActor
					setLoop: 4
					cel: 0
					setMotion: MoveTo 152 86 self
				)
			)
			(86 (= ticks 240))
			(87
				(globalSound number: 930 setLoop: 1 play:)
				(self cue:)
			)
			(88
				(iconProp setCycle: EndLoop self)
				(signActor y: 114 setCel: 1)
			)
			(89 (= ticks 300))
			(90
				(signActor setCel: 2)
				(= ticks 300)
			)
			(91 (cSound fade: 0 6 3 0 self))
			(92 (self init:))
		)
	)
	
	(method (handleEvent event)
		(event claimed: TRUE)
		(if (OneOf (event type?) keyDown mouseDown joyDown)
			(theGame setCursor: ARROW_CURSOR TRUE)
			(SetCursor 1)
			(Animate)
			(EndPrint)
		)
	)
)

(instance iconProp of Prop
	(properties
		signal ignrAct
	)
)

(instance signActor of Actor
	(properties
		signal (| ignrAct ignrHrz)
		illegalBits $0000
		moveSpeed 0
	)
)

(instance quest of GameControls

	(method (init)
		((= window (GloryWindow new:))
			top: 40
			left: 95
			bottom: 125
			right: 220
			priority: 15
			yourself:
		)
		(self add: continueIcon restoreIcon restartIcon quitIcon)
		(super init: &rest)
	)
)

(instance continueIcon of IconItem
	(properties
		view 935
		loop 2
		cel 0
		nsTop 15
		signal (| VICON RELVERIFY)
		maskView 361
		maskLoop 3
	)
	
	(method (show &tmp [temp0 15])
		(= nsRight 80)
		(= nsBottom (+ nsTop 15))
		(DrawCel view loop cel nsLeft nsTop -1)
		(Display @contBuf p_at 20 (+ nsTop 3) p_color 17)
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
		(Display @contBuf p_at 20 (+ nsTop 3) p_color sColor)
	)
)

(instance restoreIcon of IconItem
	(properties
		view 935
		loop 2
		cel 0
		nsTop 30
		signal (| VICON RELVERIFY)
		maskView 361
		maskLoop 3
	)
	
	(method (show &tmp [temp0 15])
		(= nsRight 80)
		(= nsBottom (+ nsTop 15))
		(DrawCel view loop cel nsLeft nsTop -1)
		(Display @restoreBuf p_at 20 (+ nsTop 3) p_color 17)
	)
	
	(method (select)
		(= printRet 3)
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
		(Display @restoreBuf p_at 20 (+ nsTop 3) p_color sColor)
	)
)

(instance restartIcon of IconItem
	(properties
		view 935
		loop 2
		cel 0
		nsTop 45
		signal (| VICON RELVERIFY)
		maskView 361
		maskLoop 3
	)
	
	(method (show)
		(= nsRight 80)
		(= nsBottom (+ nsTop 15))
		(DrawCel view loop cel nsLeft nsTop -1)
		(Display @restartBuf p_at 20 (+ nsTop 3) p_color 17)
	)
	
	(method (select)
		(= printRet 2)
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
		(Display @restartBuf p_at 20 (+ nsTop 3) p_color sColor)
	)
)

(instance quitIcon of IconItem
	(properties
		view 935
		loop 2
		cel 0
		nsTop 60
		signal (| VICON RELVERIFY)
		maskView 361
		maskLoop 3
	)
	
	(method (show &tmp [temp0 15])
		(= nsRight 80)
		(= nsBottom (+ nsTop 15))
		(DrawCel view loop cel nsLeft nsTop -1)
		(Display @quitBuf p_at 20 (+ nsTop 3) p_color 17)
	)
	
	(method (select)
		(= printRet 4)
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
		(Display @quitBuf p_at 20 (+ nsTop 3) p_color sColor)
	)
)
