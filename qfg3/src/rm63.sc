;;; Sierra Script 1.0 - (do not remove this comment)
(script# OPENING_CREDITS) ;63
(include game.sh)
(use Main)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm63 0
)

(local
	cred1
	cred2
	cred3
	saveCur
	local4
	local5
)
(instance rm63 of Room
	(properties
		picture 146
	)
	
	(method (init &tmp temp0)
		(super init:)
		(= local5 1)
		(= local4 0)
		(theIconBar disable:)
		(Load RES_VIEW 920)
		(Load RES_VIEW 921)
		(Load RES_VIEW 922)
		(Load RES_VIEW 923)
		(Load RES_SOUND 350)
		(Load RES_SOUND 320)
		(Load RES_SOUND 330)
		(Load RES_SOUND 230)
		(Load RES_SOUND 622)
		(Load RES_SOUND 833)
		(Load RES_SOUND 840)
		(Load RES_SOUND 845)
		(Load RES_SOUND 880)
		(Load RES_SOUND 100)
		(= saveCur
			(theGame setCursor: INVIS_CURSOR TRUE)
		)
		(SetCursor 0)
		(Palette PALIntensity 0 255 100)
	)
	
	(method (doit &tmp evt)
		(if local5
			(= local5 0)
			(doCredits init: self)
			(creditsMusic init: self)
			(= evt (Event new:))
			(while (not (OneOf (evt type?) keyDown mouseDown joyDown))
				(evt dispose:)
				(= gameTime (+ tickOffset (GetTime)))
				(doCredits doit:)
				(creditsMusic doit:)
				(sounds eachElementDo: #check)
				(sounds eachElementDo: #doit)
				(Animate (cast elements?) TRUE)
				(if doMotionCue
					(= doMotionCue FALSE)
					(cast eachElementDo: #motionCue)
				)
				(= evt (Event new:))
			)
			(evt dispose:)
			(doCredits dispose:)
			(creditsMusic dispose:)
			(curRoom newRoom: prevRoomNum)
		)
	)
	
	(method (dispose)
		(UnLoad RES_VIEW 920)
		(UnLoad RES_VIEW 921)
		(UnLoad RES_VIEW 922)
		(UnLoad RES_VIEW 923)
		(Lock RES_SOUND 350 0)
		(Lock RES_SOUND 320 0)
		(Lock RES_SOUND 330 0)
		(Lock RES_SOUND 230 0)
		(Lock RES_SOUND 622 0)
		(Lock RES_SOUND 833 0)
		(Lock RES_SOUND 840 0)
		(Lock RES_SOUND 845 0)
		(Lock RES_SOUND 100 0)
		(Lock RES_SOUND 880 0)
		(SetCursor 1)
		(theGame setCursor: saveCur TRUE)
		(theIconBar enable:)
		(theGame setScript: 0)
		(super dispose:)
	)
)

(instance doCredits of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((= cred1 (Actor new:))
					view: 920
					signal: (| ignrAct ignrHrz fixPriOn)
					setLoop: 0
					setCel: 0
					x: 160
					priority: 0
					y: 230
					moveSpeed: 2
					yStep: 3
					setMotion: MoveTo 160 71 self
					init:
				)
			)
			(1
				((= cred2 (Actor new:))
					view: 920
					signal: (| ignrAct ignrHrz fixPriOn)
					setLoop: 0
					setCel: 1
					x: 160
					y: 230
					priority: 1
					moveSpeed: 2
					yStep: 3
					setMotion: MoveTo 160 106 self
					init:
				)
			)
			(2
				((= cred3 (Actor new:))
					view: 920
					signal: (| ignrAct ignrHrz fixPriOn)
					setLoop: 0
					setCel: 2
					x: 160
					y: 230
					moveSpeed: 2
					yStep: 3
					priority: 2
					setMotion: MoveTo 160 131 self
					init:
				)
			)
			(3 (= ticks 137))
			(4
				(cred1 setMotion: MoveTo 160 -15)
				(cred2 setMotion: MoveTo 160 -15)
				(cred3 setMotion: MoveTo 160 -15 self)
			)
			(5
				(cred1 dispose:)
				(cred2 dispose:)
				(cred3 dispose:)
				(= cycles 2)
			)
			(6
				((= cred1 (Actor new:))
					view: 920
					signal: (| ignrAct ignrHrz fixPriOn)
					setLoop: 2
					setCel: 0
					x: 160
					priority: 0
					y: 230
					moveSpeed: 2
					yStep: 3
					setMotion: MoveTo 160 77 self
					init:
				)
			)
			(7
				((= cred2 (Actor new:))
					view: 920
					signal: (| ignrAct ignrHrz fixPriOn)
					setLoop: 0
					setCel: 1
					x: 160
					y: 230
					priority: 1
					moveSpeed: 2
					yStep: 3
					setMotion: MoveTo 160 106 self
					init:
				)
			)
			(8 (= ticks 137))
			(9
				(cred1 setMotion: MoveTo 160 -15)
				(cred2 setMotion: MoveTo 160 -15 self)
			)
			(10
				(cred1 dispose:)
				(cred2 dispose:)
				(= cycles 2)
			)
			(11
				((= cred1 (Actor new:))
					view: 920
					signal: (| ignrAct ignrHrz fixPriOn)
					setLoop: 4
					setCel: 0
					x: 160
					y: 230
					priority: 0
					moveSpeed: 2
					yStep: 3
					setMotion: MoveTo 160 67 self
					init:
				)
			)
			(12
				((= cred2 (Actor new:))
					view: 920
					signal: (| ignrAct ignrHrz fixPriOn)
					setLoop: 4
					setCel: 1
					x: 160
					y: 230
					priority: 1
					moveSpeed: 2
					yStep: 3
					setMotion: MoveTo 160 104 self
					init:
				)
			)
			(13 (= ticks 137))
			(14
				(cred1 setMotion: MoveTo 160 -15)
				(cred2 setMotion: MoveTo 160 -15 self)
			)
			(15
				(cred1 dispose:)
				(cred2 dispose:)
				(= cycles 2)
			)
			(16
				((= cred1 (Actor new:))
					view: 920
					signal: (| ignrAct ignrHrz fixPriOn)
					setLoop: 6
					setCel: 0
					x: 160
					y: 230
					priority: 0
					moveSpeed: 2
					yStep: 3
					setMotion: MoveTo 160 72 self
					init:
				)
			)
			(17
				((= cred2 (Actor new:))
					view: 920
					signal: (| ignrAct ignrHrz fixPriOn)
					setLoop: 6
					setCel: 1
					x: 160
					y: 230
					priority: 1
					moveSpeed: 2
					yStep: 3
					setMotion: MoveTo 160 109 self
					init:
				)
			)
			(18 (= ticks 137))
			(19
				(cred1 setMotion: MoveTo 160 -15)
				(cred2 setMotion: MoveTo 160 -15 self)
			)
			(20
				(cred1 dispose:)
				(cred2 dispose:)
				(= cycles 2)
			)
			(21
				((= cred1 (Actor new:))
					view: 920
					signal: (| ignrAct ignrHrz fixPriOn)
					setLoop: 8
					setCel: 0
					x: 160
					y: 230
					priority: 0
					moveSpeed: 2
					yStep: 3
					setMotion: MoveTo 160 83 self
					init:
				)
			)
			(22
				((= cred2 (Actor new:))
					view: 920
					signal: (| ignrAct ignrHrz fixPriOn)
					setLoop: 8
					setCel: 1
					x: 160
					y: 230
					priority: 1
					moveSpeed: 2
					yStep: 3
					setMotion: MoveTo 160 114 self
					init:
				)
			)
			(23 (= ticks 137))
			(24
				(cred1 setMotion: MoveTo 160 -15)
				(cred2 setMotion: MoveTo 160 -15 self)
			)
			(25
				(cred1 dispose:)
				(cred2 dispose:)
				(= cycles 2)
			)
			(26
				((= cred1 (Actor new:))
					view: 920
					signal: (| ignrAct ignrHrz fixPriOn)
					setLoop: 10
					setCel: 0
					x: 160
					y: 230
					priority: 0
					moveSpeed: 2
					yStep: 3
					setMotion: MoveTo 160 77 self
					init:
				)
			)
			(27
				((= cred2 (Actor new:))
					view: 920
					signal: (| ignrAct ignrHrz fixPriOn)
					setLoop: 10
					setCel: 1
					x: 160
					y: 230
					priority: 1
					moveSpeed: 2
					yStep: 3
					setMotion: MoveTo 160 111 self
					init:
				)
			)
			(28 (= ticks 137))
			(29
				(cred1 setMotion: MoveTo 160 -15)
				(cred2 setMotion: MoveTo 160 -15 self)
			)
			(30
				(cred1 dispose:)
				(cred2 dispose:)
				(= cycles 2)
			)
			(31
				((= cred1 (Actor new:))
					view: 920
					signal: (| ignrAct ignrHrz fixPriOn)
					setLoop: 12
					setCel: 0
					x: 160
					y: 230
					priority: 0
					moveSpeed: 2
					yStep: 3
					setMotion: MoveTo 160 79 self
					init:
				)
			)
			(32
				((= cred2 (Actor new:))
					view: 920
					signal: (| ignrAct ignrHrz fixPriOn)
					setLoop: 12
					setCel: 1
					x: 160
					y: 230
					priority: 1
					moveSpeed: 2
					yStep: 3
					setMotion: MoveTo 160 112 self
					init:
				)
			)
			(33 (= ticks 137))
			(34
				(cred1 setMotion: MoveTo 160 -15)
				(cred2 setMotion: MoveTo 160 -15 self)
			)
			(35
				(cred1 dispose:)
				(cred2 dispose:)
				(= cycles 2)
			)
			(36
				((= cred1 (Actor new:))
					view: 921
					signal: (| ignrAct ignrHrz fixPriOn)
					setLoop: 2
					setCel: 0
					x: 160
					y: 230
					priority: 0
					moveSpeed: 2
					yStep: 3
					setMotion: MoveTo 160 51 self
					init:
				)
			)
			(37
				((= cred2 (Actor new:))
					view: 921
					signal: (| ignrAct ignrHrz fixPriOn)
					setLoop: 2
					setCel: 1
					x: 160
					y: 245
					priority: 1
					moveSpeed: 2
					yStep: 3
					setMotion: MoveTo 160 131 self
					init:
				)
			)
			(38 (= ticks 137))
			(39
				(cred2 cel: 2)
				(DrawPic 146 PIXELDISSOLVE)
				(= ticks 237)
			)
			(40
				(cred1 setMotion: MoveTo 160 -15)
				(cred2 setMotion: MoveTo 160 -15 self)
			)
			(41
				(cred1 dispose:)
				(cred2 dispose:)
				(= cycles 2)
			)
			(42
				((= cred1 (Actor new:))
					view: 921
					signal: (| ignrAct ignrHrz fixPriOn)
					setLoop: 0
					setCel: 0
					x: 160
					y: 230
					priority: 0
					moveSpeed: 2
					yStep: 3
					setMotion: MoveTo 160 40 self
					init:
				)
			)
			(43
				((= cred2 (Actor new:))
					view: 921
					signal: (| ignrAct ignrHrz fixPriOn)
					setLoop: 0
					setCel: 1
					x: 160
					y: 270
					priority: 1
					moveSpeed: 2
					yStep: 3
					setMotion: MoveTo 160 140 self
					init:
				)
			)
			(44 (= ticks 137))
			(45
				(cred2 cel: 2)
				(DrawPic 146 PIXELDISSOLVE)
				(= ticks 237)
			)
			(46
				(cred1 setMotion: MoveTo 160 -15)
				(cred2 setMotion: MoveTo 160 -15 self)
			)
			(47
				(cred1 dispose:)
				(cred2 dispose:)
				(= cycles 2)
			)
			(48
				((= cred1 (Actor new:))
					view: 921
					signal: (| ignrAct ignrHrz fixPriOn)
					setLoop: 4
					setCel: 0
					x: 160
					y: 230
					priority: 0
					moveSpeed: 2
					yStep: 3
					setMotion: MoveTo 160 71 self
					init:
				)
			)
			(49
				((= cred2 (Actor new:))
					view: 921
					signal: (| ignrAct ignrHrz fixPriOn)
					setLoop: 4
					setCel: 1
					x: 160
					y: 230
					priority: 1
					moveSpeed: 2
					yStep: 3
					setMotion: MoveTo 160 106 self
					init:
				)
			)
			(50 (= ticks 137))
			(51
				(cred1 setMotion: MoveTo 160 -15)
				(cred2 setMotion: MoveTo 160 -15 self)
			)
			(52
				(cred1 dispose:)
				(cred2 dispose:)
				(= cycles 2)
			)
			(53
				((= cred1 (Actor new:))
					view: 922
					signal: (| ignrAct ignrHrz fixPriOn)
					setLoop: 0
					setCel: 0
					x: 160
					y: 230
					priority: 0
					moveSpeed: 2
					yStep: 3
					setMotion: MoveTo 160 58 self
					init:
				)
			)
			(54
				((= cred2 (Actor new:))
					view: 922
					signal: (| ignrAct ignrHrz fixPriOn)
					setLoop: 0
					setCel: 1
					x: 160
					y: 245
					priority: 1
					moveSpeed: 2
					yStep: 3
					setMotion: MoveTo 160 144 self
					init:
				)
			)
			(55 (= ticks 137))
			(56
				(cred2 cel: 2)
				(DrawPic 146 PIXELDISSOLVE)
				(= ticks 237)
			)
			(57
				(cred2 cel: 3)
				(DrawPic 146 PIXELDISSOLVE)
				(= ticks 237)
			)
			(58
				(cred2 cel: 4)
				(DrawPic 146 PIXELDISSOLVE)
				(= ticks 237)
			)
			(59
				(cred1 setMotion: MoveTo 160 -15)
				(cred2 setMotion: MoveTo 160 -15 self)
			)
			(60
				(cred1 dispose:)
				(cred2 dispose:)
				(= cycles 2)
			)
			(61
				((= cred1 (Actor new:))
					view: 922
					signal: (| ignrAct ignrHrz fixPriOn)
					setLoop: 2
					setCel: 0
					x: 160
					y: 230
					priority: 0
					moveSpeed: 2
					yStep: 3
					setMotion: MoveTo 160 54 self
					init:
				)
			)
			(62
				((= cred2 (Actor new:))
					view: 922
					signal: (| ignrAct ignrHrz fixPriOn)
					setLoop: 2
					setCel: 1
					x: 160
					y: 245
					priority: 1
					moveSpeed: 2
					yStep: 3
					setMotion: MoveTo 160 132 self
					init:
				)
			)
			(63 (= ticks 137))
			(64
				(cred1 setMotion: MoveTo 160 -15)
				(cred2 setMotion: MoveTo 160 -15 self)
			)
			(65
				(cred1 dispose:)
				(cred2 dispose:)
				(= cycles 2)
			)
			(66
				((= cred1 (Actor new:))
					view: 922
					signal: (| ignrAct ignrHrz fixPriOn)
					setLoop: 4
					setCel: 0
					x: 160
					y: 230
					priority: 0
					moveSpeed: 2
					yStep: 3
					setMotion: MoveTo 160 73 self
					init:
				)
			)
			(67
				((= cred2 (Actor new:))
					view: 922
					signal: (| ignrAct ignrHrz fixPriOn)
					setLoop: 4
					setCel: 1
					x: 160
					y: 245
					priority: 1
					moveSpeed: 2
					yStep: 3
					setMotion: MoveTo 160 111 self
					init:
				)
			)
			(68 (= ticks 137))
			(69
				(cred1 setMotion: MoveTo 160 -15)
				(cred2 setMotion: MoveTo 160 -15 self)
			)
			(70
				(cred1 dispose:)
				(cred2 dispose:)
				(= cycles 2)
			)
			(71
				((= cred1 (Actor new:))
					view: 923
					signal: (| ignrAct ignrHrz fixPriOn)
					setLoop: 0
					setCel: 0
					x: 160
					y: 230
					priority: 0
					moveSpeed: 2
					yStep: 3
					setMotion: MoveTo 160 72 self
					init:
				)
			)
			(72
				((= cred2 (Actor new:))
					view: 923
					signal: (| ignrAct ignrHrz fixPriOn)
					setLoop: 0
					setCel: 1
					x: 160
					y: 245
					priority: 1
					moveSpeed: 2
					yStep: 3
					setMotion: MoveTo 160 107 self
					init:
				)
			)
			(73 (= ticks 137))
			(74
				(cred1 setMotion: MoveTo 160 -15)
				(cred2 setMotion: MoveTo 160 -15 self)
			)
			(75
				(cred1 dispose:)
				(cred2 dispose:)
				(= cycles 2)
			)
			(76
				((= cred1 (Actor new:))
					view: 923
					signal: (| ignrAct ignrHrz fixPriOn)
					setLoop: 2
					setCel: 0
					x: 160
					y: 230
					priority: 0
					moveSpeed: 2
					yStep: 3
					setMotion: MoveTo 160 54 self
					init:
				)
			)
			(77
				((= cred2 (Actor new:))
					view: 923
					signal: (| ignrAct ignrHrz fixPriOn)
					setLoop: 2
					setCel: 1
					x: 160
					y: 245
					priority: 1
					moveSpeed: 2
					yStep: 3
					setMotion: MoveTo 160 145 self
					init:
				)
			)
			(78 (= ticks 137))
			(79
				(cred2 cel: 2)
				(DrawPic 146 PIXELDISSOLVE)
				(= ticks 237)
			)
			(80
				(cred1 setMotion: MoveTo 160 -15)
				(cred2 setMotion: MoveTo 160 -15 self)
			)
			(81
				(cred1 dispose:)
				(cred2 dispose:)
				(= cycles 2)
			)
			(82
				((= cred1 (Actor new:))
					view: 922
					signal: (| ignrAct ignrHrz fixPriOn)
					setLoop: 6
					setCel: 0
					x: 160
					y: 230
					priority: 0
					moveSpeed: 2
					yStep: 3
					setMotion: MoveTo 160 53 self
					init:
				)
			)
			(83
				((= cred2 (Actor new:))
					view: 922
					signal: (| ignrAct ignrHrz fixPriOn)
					setLoop: 6
					setCel: 1
					x: 160
					y: 245
					priority: 1
					moveSpeed: 2
					yStep: 3
					setMotion: MoveTo 160 134 self
					init:
				)
			)
			(84 (= ticks 137))
			(85
				(cred2 cel: 2)
				(DrawPic 146 PIXELDISSOLVE)
				(= ticks 237)
			)
			(86
				(cred1 setMotion: MoveTo 160 -15)
				(cred2 setMotion: MoveTo 160 -15 self)
			)
			(87
				(cred1 dispose:)
				(cred2 dispose:)
				(= cycles 2)
			)
			(88
				((= cred1 (Actor new:))
					view: 923
					signal: (| ignrAct ignrHrz fixPriOn)
					setLoop: 4
					setCel: 0
					x: 160
					y: 230
					priority: 0
					moveSpeed: 2
					yStep: 3
					setMotion: MoveTo 160 46 self
					init:
				)
			)
			(89
				((= cred2 (Actor new:))
					view: 923
					signal: (| ignrAct ignrHrz fixPriOn)
					setLoop: 4
					setCel: 1
					x: 160
					y: 245
					priority: 1
					moveSpeed: 2
					yStep: 3
					setMotion: MoveTo 160 137 self
					init:
				)
			)
			(90 (= ticks 137))
			(91
				(cred2 cel: 2)
				(DrawPic 146 PIXELDISSOLVE)
				(= ticks 237)
			)
			(92
				(cred1 setMotion: MoveTo 160 -15)
				(cred2 setMotion: MoveTo 160 -15 self)
			)
			(93
				(cred1 dispose:)
				(cred2 dispose:)
				(= cycles 2)
			)
			(94 (self init:))
		)
	)
)

(instance creditsMusic of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cSound setLoop: 1 number: 350 play: self)
			)
			(1
				(cSound setLoop: 1 number: 320 play: self)
			)
			(2
				(cSound setLoop: 1 number: 330 play: self)
			)
			(3
				(cSound setLoop: 1 number: 230 play: self)
			)
			(4
				(cSound setLoop: 1 number: 622 play: self)
			)
			(5
				(cSound setLoop: 1 number: 833 play: self)
			)
			(6
				(cSound setLoop: 1 number: 840 play: self)
			)
			(7
				(cSound setLoop: 1 number: 845 play: self)
			)
			(8
				(cSound setLoop: 1 number: 100 play: self)
			)
			(9
				(cSound setLoop: 1 number: 880 play: self)
			)
			(10 (self init:))
		)
	)
	
	(method (cue)
		(cond 
			((not local4)
				(= local4 1)
				(self changeState: (+ state 1) &rest)
			)
			((== (cSound prevSignal?) -1)
				(self changeState: (+ state 1) &rest)
			)
		)
	)
)
