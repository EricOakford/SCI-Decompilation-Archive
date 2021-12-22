;;; Sierra Script 1.0 - (do not remove this comment)
(script# 140)
(include sci.sh)
(use Main)
(use fileScr)
(use LarryRoom)
(use n098)
(use Timer)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm140 0
)

(local
	[local0 6] = [154 153 145 152 151 149]
	theNewRoomNumber
	local7
	local8 =  60
	[local9 2] = [1 -1]
	newView
	newView_2
	newView_3
	newProp
	local15
	local16 =  -1
)
(procedure (localproc_006e param1 param2 param3)
	(monitorL setCycle: 0)
	(monitorR setCycle: 0)
	(switch param1
		(86
			(switch param2
				(94
					(monitorL
						view: 154
						setLoop: 4
						cel: 0
						cycleSpeed: 10
						setCycle: End
					)
					(monitorR
						view: 154
						setLoop: 4
						cel: 0
						cycleSpeed: 10
						setCycle: End param3
					)
				)
				(92
					(monitorL view: 154 setLoop: 4 cel: 0)
					(monitorR view: 154 setLoop: 4 cel: 0)
				)
				(98
					(monitorL view: 154 setLoop: 3 cel: 0)
					(monitorR view: 154 setLoop: 3 cel: 0)
				)
				(99
					(monitorL view: 154 setLoop: 3 cel: 1)
					(monitorR view: 154 setLoop: 3 cel: 1)
				)
				(else 
					(monitorL view: 154 setLoop: 4 cel: 14)
					(monitorR view: 154 setLoop: 4 cel: 14)
				)
			)
		)
		(84
			(monitorL
				view: 153
				setLoop: 6
				cel: 0
				cycleSpeed: 18
				setCycle: End param3
			)
			(monitorR
				view: 153
				setLoop: 6
				cel: 0
				cycleSpeed: 18
				setCycle: End
			)
		)
		(83
			(monitorL
				view: 153
				setLoop: 5
				cel: 0
				cycleSpeed: 8
				setCycle: End param3
			)
			(monitorR
				view: 153
				setLoop: 5
				cel: 0
				cycleSpeed: 8
				setCycle: End
			)
		)
		(87
			(monitorL view: 145 setLoop: 3 cel: 3)
			(monitorR view: 145 setLoop: 3 cel: 3)
		)
		(89
			(monitorL view: 145 setLoop: 3 cel: 0)
			(monitorR view: 145 setLoop: 3 cel: 0)
		)
		(90
			(monitorL view: 145 setLoop: 3 cel: 1)
			(monitorR view: 145 setLoop: 3 cel: 1)
		)
		(91
			(monitorL view: 145 setLoop: 3 cel: 2)
			(monitorR view: 145 setLoop: 3 cel: 2)
		)
		(85
			(monitorL view: 152 setLoop: 2 cel: 0)
			(monitorR view: 152 setLoop: 2 cel: 0)
		)
		(88
			(switch param2
				(93
					(monitorL view: 151 setLoop: 0 cycleSpeed: 6 cel: 0)
					(monitorR view: 151 setLoop: 0 cycleSpeed: 6 cel: 0)
				)
				(95
					(monitorL view: 151 setLoop: 1 cel: 0)
					(monitorR view: 151 setLoop: 1 cel: 0)
				)
				(92
					(monitorL view: 151 setLoop: 0 cel: 0)
					(monitorR view: 151 setLoop: 0 cel: 0)
				)
			)
		)
		(else 
			(monitorL view: 145 setLoop: 7 cel: 0)
			(monitorR view: 145 setLoop: 7 cel: 0)
		)
	)
)

(procedure (localproc_0434 param1)
	(switch param1
		(96
			(applauseSign setCycle: Fwd)
			(applause play: setLoop: -1)
		)
		(97
			(applause fade: 0 10 10 1)
			(applauseSign cel: 0 setCycle: 0)
		)
	)
)

(class cObj of Obj
	(properties
		scratch 0
	)
)

(instance rm140 of LarryRoom
	(properties
		picture 140
		horizon 0
	)
	
	(method (init)
		(SetCursor 98 0 0)
		(theMusic2 number: 0 stop:)
		(super init: &rest)
		(Palette 2 0 255 local8)
		(mouseDownHandler add: self)
		(keyDownHandler add: self)
		(self setScript: cartoonScr)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(super dispose: &rest)
	)
	
	(method (handleEvent event)
		(if
			(and
				(not (event claimed?))
				(or
					(& (event type?) evMOUSEBUTTON)
					(and
						(not theNewRoomNumber)
						(== (event message?) KEY_RETURN)
						(& (event type?) evKEYBOARD)
					)
				)
			)
			(event claimed: 1)
			(proc98_0)
		)
	)
	
	(method (cue)
		(roomTimer dispose: delete:)
		(Palette 2 0 255 100)
		(super newRoom: theNewRoomNumber)
	)
	
	(method (newRoom n)
		(= theNewRoomNumber n)
		(cast eachElementDo: #hide)
		(thePlane drawPic: -1)
		(roomTimer setReal: self 2)
		(if global205 (proc79_7))
	)
)

(instance roomTimer of Timer
	(properties)
)

(instance biff of Actor
	(properties
		x -18
		y 131
		view 144
		cel 6
		signal $6021
		cycleSpeed 4
		moveSpeed 4
	)
)

(instance rockHard of Prop
	(properties
		x 118
		y 112
		priority 112
		fixPriority 1
		view 140
		loop 1
		cel 3
		signal $4821
	)
)

(instance girl1 of Prop
	(properties
		x 200
		y 108
		priority 108
		fixPriority 1
		view 140
		loop 1
		signal $4821
	)
)

(instance girl2 of Prop
	(properties
		x 217
		y 110
		priority 110
		fixPriority 1
		view 140
		loop 1
		cel 1
		signal $4821
	)
)

(instance girl3 of Prop
	(properties
		x 233
		y 112
		priority 112
		fixPriority 1
		view 140
		loop 1
		cel 2
		signal $4821
	)
)

(instance monitorL of Prop
	(properties
		x 281
		y 13
		priority 30
		fixPriority 1
		view 145
		loop 7
		signal $4821
	)
)

(instance monitorR of Prop
	(properties
		x 19
		y 13
		priority 30
		fixPriority 1
		view 145
		loop 7
		signal $4821
	)
)

(instance waveLeft of Prop
	(properties
		x 120
		y 40
		priority 40
		fixPriority 1
		view 142
		loop 1
		cel 4
		signal $4821
		cycleSpeed 15
	)
)

(instance waveRight of Prop
	(properties
		x 195
		y 40
		priority 40
		fixPriority 1
		view 142
		cel 7
		signal $4821
		cycleSpeed 15
	)
)

(instance applauseSign of Prop
	(properties
		x 204
		y 2
		view 149
		cel 1
	)
)

(instance drumFx of Sound
	(properties
		flags $0004
		number 142
	)
)

(instance applause of Sound
	(properties
		flags $0004
		number 141
		loop -1
	)
)

(instance sfx of Sound
	(properties)
)

(instance shalo of Actor
	(properties
		x -18
		y 131
		view 144
		cel 6
		signal $4821
		cycleSpeed 4
		moveSpeed 4
	)
	
	(method (cue)
		(super cue:)
		(switch (++ local7)
			(1
				(self
					cycleSpeed: 4
					moveSpeed: 4
					setMotion: MoveTo 35 135 self
				)
			)
			(2
				(self setMotion: MoveTo -15 130 self)
			)
			(3 (self view: 98 dispose:))
		)
	)
)

(instance fidgetScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client view: 140 setLoop: 1 cel: register)
				(= seconds (Random 5 20))
			)
			(1
				(client view: 141 setLoop: register cel: 0)
				(= ticks 30)
			)
			(2 (client setCycle: End self))
			(3 (= ticks 90))
			(4 (self init:))
		)
	)
)

(instance ffScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((= newView (View new:))
					view: 143
					setLoop: 0
					cel: 0
					x: 0
					y: 24
					priority: 199
					fixPriority: 1
					signal: 18465
					init:
					hide:
				)
				((= newView_2 (View new:))
					view: 143
					setLoop: 1
					cel: 0
					x: 0
					y: 73
					priority: 199
					fixPriority: 1
					signal: 18465
					init:
					hide:
				)
				((= newView_3 (View new:))
					view: 143
					setLoop: 2
					cel: 0
					x: 0
					y: 116
					fixPriority: 1
					priority: 199
					signal: 18465
					init:
					hide:
				)
				((= newProp (Prop new:))
					view: 143
					setLoop: 3
					cel: 0
					x: 154
					y: 139
					priority: 200
					fixPriority: 1
					signal: 18465
					init:
					setCycle: End self
				)
			)
			(1
				(theMusic number: 145 loop: -1 play: setVol: 80)
				(waveLeft cycleSpeed: 0)
				(waveRight cycleSpeed: 0)
				(= register 0)
				(= local15 60)
				(= cycles 2)
			)
			(2
				(switch (Random 0 30)
					(1 (localproc_006e 86 99))
					(2 (localproc_006e 88 92))
					(3 (localproc_006e 88 95))
					(4 (localproc_006e 85 92))
					(5 (localproc_006e 89))
					(6 (localproc_006e 90))
					(7 (localproc_006e 91))
					(8 (localproc_006e 87))
				)
				(switch (++ register)
					(1
						(newView_3 hide:)
						(newView show:)
					)
					(2
						(newView hide:)
						(newView_2 show:)
					)
					(3
						(newView_2 hide:)
						(newView_3 show:)
						(= register 0)
					)
				)
				(if (-- local15)
					(-- state)
					(= cycles 1)
				else
					(newProp cycleSpeed: 12 setCycle: CT 6 -1 self)
				)
			)
			(3
				(waveLeft cycleSpeed: 15)
				(waveRight cycleSpeed: 15)
				(newView dispose:)
				(newView_2 dispose:)
				(newView_3 dispose:)
				(= ticks 10)
			)
			(4
				(newProp cycleSpeed: 6 setCycle: Beg self)
			)
			(5
				(theMusic number: 140 loop: -1 play: setVol: 80)
				(newProp dispose:)
				(self dispose:)
			)
		)
	)
)

(instance cartoonScr of Script
	(properties)
	
	(method (dispose &tmp temp0)
		(= temp0 0)
		(while (<= temp0 5)
			(Lock 128 [local0 temp0] 0)
			(UnLoad 128 [local0 temp0])
			(++ temp0)
		)
		(sfx number: 0 dispose:)
		(proc79_12 144)
		(super dispose:)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(= temp0 0)
				(while (<= temp0 5)
					(Load rsVIEW [local0 temp0])
					(Lock 128 [local0 temp0] 1)
					(++ temp0)
				)
				(applauseSign init: cel: 0)
				(shalo
					view: 146
					setLoop: 0 1
					x: 47
					y: 124
					setCycle: Walk
					init:
					setMotion: MoveTo 67 124 self
				)
				(rockHard init: setScript: (fidgetScr new:) 0 3)
				(girl1 init: setScript: (fidgetScr new:) 0 0)
				(girl2 init: setScript: (fidgetScr new:) 0 1)
				(girl3 init: setScript: (fidgetScr new:) 0 2)
				(monitorL init:)
				(monitorR init:)
				(ego
					init:
					view: 140
					setLoop: 1
					cel: 4
					x: 88
					y: 114
					setPri: 30
				)
			)
			(1
				(if (not global205) (proc79_8 25))
				(shalo setLoop: 3 cel: 0)
				(= ticks 30)
			)
			(2
				(messager sayRange: 0 0 1 1 3 self)
			)
			(3
				(shalo setLoop: 2 cel: 3)
				(= ticks 30)
			)
			(4 (shalo cel: 1) (= ticks 30))
			(5
				(shalo
					setLoop: 1
					setCycle: Fwd
					cycleSpeed: 6
					moveSpeed: 6
					setMotion: MoveTo 52 125 self
				)
			)
			(6
				(shalo cue:)
				(messager sayRange: 0 0 1 4 6 self)
			)
			(7
				(if global205 (proc79_7))
				(messager say: 0 0 1 7)
				(= ticks 240)
			)
			(8
				(if (<= (++ local8) 100)
					(Palette 2 0 255 local8)
					(-- state)
				)
				(= ticks 3)
			)
			(9
				(if (talkers size:) (-- state))
				(= ticks 10)
			)
			(10
				(theMusic
					number: 140
					loop: -1
					play:
					setVol: 80
					fade: 127 10 10 0
				)
				(waveLeft init: setCycle: Fwd)
				(waveRight init: setCycle: Fwd)
				(= ticks 30)
			)
			(11
				(proc79_11 144)
				(biff view: 98 setLoop: 0 x: -15 y: 130 init:)
				(localproc_0434 96)
				(localproc_006e 86 92)
				(messager say: 0 0 1 8 self)
			)
			(12 (= ticks 60))
			(13
				(sfx number: 144 loop: 1 play: self)
			)
			(14
				(messager say: 0 0 1 9 self)
			)
			(15 (localproc_006e 86 94 self))
			(16 (= ticks 60))
			(17
				(localproc_006e 89)
				(messager say: 0 0 1 10 self)
			)
			(18
				(localproc_006e 90)
				(= ticks 120)
			)
			(19
				(localproc_006e 91)
				(= ticks 120)
			)
			(20
				(localproc_006e 87)
				(messager say: 0 0 1 11 self)
			)
			(21
				(localproc_006e 88 92)
				(= ticks 120)
			)
			(22
				(messager say: 0 0 1 12 self)
			)
			(23
				(sfx number: 144 loop: 1 play: self)
				(localproc_006e 86)
			)
			(24
				(messager say: 0 0 1 13)
				(biff
					view: 144
					setLoop: 0 1
					setCycle: Walk
					cycleSpeed: 2
					moveSpeed: 2
					setMotion: MoveTo 35 135 self
				)
			)
			(25
				(localproc_006e 87)
				(biff setMotion: MoveTo 52 125 self)
			)
			(26
				(biff setMotion: MoveTo 158 109 self)
			)
			(27
				(localproc_006e 88 92)
				(= ticks 120)
			)
			(28
				(biff view: 150 setLoop: 3 cel: 2)
				(= ticks 60)
			)
			(29
				(localproc_006e 86 98)
				(biff setLoop: 3 cycleSpeed: 6 setCycle: Fwd)
				(= ticks 90)
			)
			(30
				(biff setLoop: 4 cel: 0 setCycle: 0)
				(= ticks 30)
			)
			(31
				(localproc_0434 97)
				(theMusic fade: 100 10 10 0)
				(biff cycleSpeed: 12 setCycle: End self)
			)
			(32 (= ticks 60))
			(33
				(biff cycleSpeed: 12 setCycle: Beg self)
			)
			(34
				(localproc_006e 85 92)
				(biff view: 147)
				(cond 
					((> howFast 10) (= ticks 240))
					((> howFast 3) (= ticks 120))
					(else (= ticks 60))
				)
			)
			(35
				(localproc_006e 85 93)
				(messager say: 0 0 1 14 self)
			)
			(36
				(localproc_006e 88 92)
				(messager say: 0 0 1 15 self)
			)
			(37
				(localproc_0434 96)
				(= ticks 120)
			)
			(38
				(localproc_0434 97)
				(localproc_006e 87)
				(messager say: 0 0 1 16 self)
			)
			(39
				(localproc_0434 96)
				(= ticks 120)
			)
			(40
				(localproc_006e 89)
				(messager sayRange: 0 0 1 17 18 self)
			)
			(41
				(localproc_006e 90)
				(localproc_0434 97)
				(= ticks 120)
			)
			(42
				(localproc_006e 91)
				(localproc_0434 96)
				(= ticks 120)
			)
			(43
				(localproc_006e 85 92)
				(messager say: 0 0 1 19 self)
			)
			(44 (= ticks 30))
			(45
				(localproc_0434 97)
				(messager say: 0 0 1 20 self)
			)
			(46
				(localproc_006e 89)
				(messager say: 0 0 2 1 self)
			)
			(47
				(localproc_0434 96)
				(= ticks 240)
			)
			(48
				(localproc_0434 97)
				(localproc_006e 90)
				(messager say: 0 0 2 2 self)
			)
			(49
				(localproc_0434 96)
				(= ticks 240)
			)
			(50
				(localproc_0434 97)
				(localproc_006e 91)
				(messager say: 0 0 2 3 self)
			)
			(51
				(localproc_0434 96)
				(= ticks 240)
			)
			(52
				(localproc_006e 85 92)
				(= ticks 120)
			)
			(53
				(localproc_0434 97)
				(biff view: 150 setLoop: 5 cel: 0 setCycle: End self)
			)
			(54
				(biff view: 148)
				(localproc_006e 85 93)
				(messager say: 0 0 2 4 self)
			)
			(55
				(localproc_006e 88 95)
				(= ticks 120)
			)
			(56
				(localproc_006e 88 93)
				(= ticks 120)
			)
			(57
				(messager say: 0 0 2 5 self)
			)
			(58
				(localproc_006e 88 92)
				(= ticks 120)
			)
			(59
				(localproc_006e 88 93)
				(messager say: 0 0 2 6 self)
			)
			(60
				(localproc_006e 88 95)
				(messager say: 0 0 2 7 self)
				(self setScript: ffScr self)
			)
			(61)
			(62
				(if (talkers size:) (messager cue: 1))
				(= ticks 30)
			)
			(63
				(localproc_006e 85 93)
				(messager say: 0 0 2 8 self)
			)
			(64
				(localproc_006e 88 93)
				(messager say: 0 0 2 9 self)
			)
			(65
				(localproc_006e 88 92)
				(messager say: 0 0 2 10 self)
				(self setScript: ffScr self)
			)
			(66)
			(67
				(localproc_0434 96)
				(localproc_006e 85 93)
				(messager say: 0 0 2 11 self)
			)
			(68
				(localproc_006e 85 93)
				(messager say: 0 0 2 12 self)
			)
			(69 (localproc_006e 84 94 self))
			(70
				(cond 
					((> howFast 10) (= ticks 120))
					((> howFast 3) (= ticks 90))
					(else (= ticks 60))
				)
			)
			(71
				(localproc_006e 88 92)
				(messager say: 0 0 2 13 self)
			)
			(72 (localproc_006e 83 94 self))
			(73
				(cond 
					((> howFast 10) (= ticks 120))
					((> howFast 3) (= ticks 90))
					(else (= ticks 60))
				)
			)
			(74
				(localproc_006e 85 93)
				(messager say: 0 0 2 14 self)
			)
			(75
				(theMusic fade: 127 10 5 0)
				(localproc_006e 86 99)
				(sfx number: 144 loop: 1 play: self)
			)
			(76
				(messager say: 0 0 2 15 self)
			)
			(77
				(cond 
					((> howFast 10) (= ticks 120))
					((> howFast 3) (= ticks 90))
					(else (= ticks 60))
				)
			)
			(78
				(waveLeft dispose:)
				(waveRight dispose:)
				(theMusic fade:)
				(localproc_0434 97)
				(localproc_006e 0)
				(Palette 2 0 255 60)
				(cond 
					((> howFast 10) (= ticks 240))
					((> howFast 3) (= ticks 120))
					(else (= ticks 60))
				)
			)
			(79
				(shalo
					view: 146
					setLoop: 0
					x: -15
					y: 130
					setCycle: Walk
					cycleSpeed: 4
					moveSpeed: 4
					init:
					setMotion: MoveTo 35 135 self
				)
			)
			(80
				(shalo setMotion: MoveTo 52 125 self)
			)
			(81
				(shalo setMotion: MoveTo 135 117 self)
			)
			(82
				(shalo setLoop: 4 cel: 0)
				(= ticks 60)
			)
			(83
				(shalo cel: 1)
				(= ticks 30)
			)
			(84
				(messager say: 0 0 2 16 self)
			)
			(85
				((ScriptID 1823 33) name: {Rock Hard})
				(messager say: 0 0 2 17 self)
			)
			(86
				(shalo cel: 0)
				(= ticks 30)
			)
			(87
				(shalo
					setLoop: 1
					setCycle: Walk
					setMotion: MoveTo 102 124 self
				)
			)
			(88
				(shalo setLoop: 2 cel: 3)
				(= ticks 30)
			)
			(89
				(messager say: 0 0 2 18 self)
			)
			(90
				(= local7 0)
				(shalo
					setLoop: 1
					setCycle: Walk
					setMotion: MoveTo 52 125 shalo
				)
				(= ticks 60)
			)
			(91
				(ego view: 900 setLoop: 8 cel: 2)
				(= ticks 60)
			)
			(92
				(messager say: 0 0 2 19 self)
			)
			(93
				(cond 
					((> howFast 10) (= ticks 120))
					((> howFast 3) (= ticks 90))
					(else (= ticks 60))
				)
			)
			(94
				(ego
					normalize: 900 2
					cycleSpeed: 6
					moveSpeed: 6
					setLoop: 1 1
					setCycle: Walk
					setMotion: MoveTo 52 125 self
				)
			)
			(95
				(ego setMotion: MoveTo 35 135 self)
			)
			(96
				(ego setMotion: MoveTo -15 130 self)
			)
			(97 (= ticks 30))
			(98
				(messager say: 0 0 2 20 self)
			)
			(99 (= ticks 30))
			(100
				(ego view: 98 x: 100 y: 100)
				(cast eachElementDo: #dispose)
				(theGame hideControls:)
				(thePlane drawPic: -1 10)
				(= cycles 2)
			)
			(101
				(Palette 2 0 255 100)
				(= ticks 60)
			)
			(102
				(messager sayRange: 0 0 2 21 23 self)
			)
			(103 (curRoom newRoom: 800))
		)
	)
)
