;;; Sierra Script 1.0 - (do not remove this comment)
(script# 53)
(include game.sh)
(use Main)
(use Intrface)
(use LoadMany)
(use StopWalk)
(use DPath)
(use Grooper)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm53 0
)

(procedure (cls)
	(RedrawCast)
	(if modelessDialog
		(modelessDialog dispose:)
	)
)

(instance rm53 of Room
	(properties
		picture 54
		horizon 102
	)
	
	(method (init)
		(self style: WIPEUP)
		(Load SOUND 101)
		(LoadMany PICTURE 53 54 99)
		(LoadMany VIEW 46 82 253 255 5 0 553 191 190 194 192 49)
		(super init:)
		(ego posn: 262 187 loop: 3 init:)
		(NormalEgo)
		(self setScript: endCartoon)
	)
)

(instance endCartoon of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Print 53 0 #at -1 20 #width 280 #mode 1 #dispose)
				(ego view: 82 loop: 0 cel: 0)
				(head
					posn: (+ (ego x?) 1) (- (ego y?) 38)
					setLoop: 1
					cel: 3
					cycleSpeed: 2
					init:
					setCycle: EndLoop
				)
				(= seconds 6)
			)
			(1
				(cls)
				(head hide:)
				(ego
					view: 46
					cycleSpeed: 0
					setStep: 3 3
					setCycle: Walk
					setLoop: 0
					setMotion: MoveTo 158 102 self
				)
			)
			(2
				(Print 53 1 #at -1 130 #width 280 #mode 1 #dispose)
				(ego view: 82 loop: 0 cel: 1)
				(head
					posn: (+ (ego x?) 1) (- (ego y?) 38)
					setLoop: 2
					cel: 4
					show:
					setCycle: EndLoop
				)
				(= seconds 6)
			)
			(3
				(cls)
				(head dispose:)
				(ego
					view: 46
					setLoop: 0
					cycleSpeed: 1
					moveSpeed: 1
					setCycle: Walk
					setMotion: MoveTo 83 57 self
				)
			)
			(4
				(Print 53 2 #at -1 130 #width 280 #mode 1 #dispose)
				(= seconds 6)
			)
			(5
				(cls)
				(ego
					view: 46
					cycleSpeed: 0
					moveSpeed: 0
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo -10 34 self
				)
			)
			(6
				(curRoom picture: 53 style: 2 drawPic: 53)
				(ego posn: 330 144 loop: 1)
				(king init:)
				(doctor init:)
				(rs1and2Body init:)
				(rs2Arm init: setCycle: Forward)
				(rs2Head init:)
				(rs1Head init:)
				(crowd1 init:)
				(addToPics
					add: crowd2 crowd2Part1
					eachElementDo: #init
					doit:
				)
				(crowd3 init:)
				(crowd1Head1 init:)
				(crowd1Head2 init:)
				(crowd1Head3 init:)
				(crowd1Head4 init:)
				(crowd2Head1 init:)
				(crowd2Head2 init:)
				(crowd2Head3 init:)
				(crowd3Head1 init:)
				(self cue:)
				(NormalEgo)
			)
			(7
				(head
					view: 192
					posn: (+ (doctor x?) 1) (- (doctor y?) 38)
					loop: 9
					setPri: 9
					cycleSpeed: 1
					setCycle: Forward
					init:
				)
				(Print 53 3 #at -1 20 #width 280 #mode 1 #dispose)
				(= seconds 6)
			)
			(8
				(cls)
				(Print 53 4 #at -1 115 #width 280 #mode 1 #dispose)
				(= seconds 9)
			)
			(9
				(cls)
				(head hide:)
				(ego setCycle: StopWalk 2 setMotion: MoveTo 312 144)
				(= seconds 3)
			)
			(10
				(self setScript: crowdTalk self)
				(doctor setCycle: EndLoop)
				(ego
					illegalBits: 0
					setMotion: DPath 207 144 173 123 139 123 self
				)
			)
			(11 (= cycles 0))
			(12
				(crowd2Head1 stopUpd:)
				(crowd2Head2 stopUpd:)
				(crowd2Head3 stopUpd:)
				(ego view: 5 setLoop: 0 setCel: 0 setCycle: EndLoop self)
			)
			(13
				(DisposeScript DPATH)
				(Print 53 5
					#at -1 20
					#width 280
					#mode teJustCenter
					#dispose
				)
				(gramHead
					init:
					setCycle: Forward
					cycleSpeed: 1
					setPri: (+ (ego priority?) 1)
				)
				(= seconds 5)
			)
			(14
				(gramHead hide:)
				(king setCycle: BegLoop self)
			)
			(15
				(cls)
				(talkingHead
					init:
					startUpd:
					setCycle: Forward
					cycleSpeed: 1
					setPri: 10
				)
				(Print 53 6
					#at -1 20
					#width 280
					#mode teJustCenter
					#dispose
				)
				(= seconds 5)
			)
			(16
				(talkingHead dispose:)
				(gramHead show:)
				(Print 53 7
					#at -1 20
					#width 280
					#mode teJustCenter
					#dispose
				)
				(= seconds 4)
			)
			(17
				(cls)
				(gramHead dispose:)
				(Print 53 8 #at -1 20 #width 280 #mode 1 #dispose)
				(ego setCycle: BegLoop self)
			)
			(18
				(cls)
				(ego
					view: 0
					setLoop: -1
					setCycle: StopWalk 2
					setMotion: MoveTo (- (ego x?) 10) (+ (ego y?) 15) self
				)
			)
			(19
				(ego setMotion: MoveTo 80 146 self)
			)
			(20
				(ego
					view: 49
					ignoreActors:
					loop: 0
					cel: 0
					setCycle: CycleTo 3 1 self
				)
			)
			(21
				(Print 53 9 #at -1 20 #width 280 #mode 1 #dispose)
				(= seconds 3)
			)
			(22
				(addToPics add: mirror eachElementDo: #init doit:)
				(ego setCycle: EndLoop self)
			)
			(23
				(ego
					view: 0
					looper: GradualLooper
					setCycle: StopWalk 2
					setMotion: MoveTo 104 146 self
				)
			)
			(24
				(cls)
				(ego view: 49 loop: 2 cel: 0 setCycle: CycleTo 3 1 self)
			)
			(25
				(Print 53 10 #at -1 20 #width 280 #mode 1 #dispose)
				(= seconds 3)
			)
			(26
				(addToPics add: shield eachElementDo: #init doit:)
				(ego setCycle: EndLoop self)
			)
			(27
				(ego
					view: 0
					looper: GradualLooper
					setCycle: StopWalk 2
					setMotion: MoveTo 137 146 self
				)
			)
			(28
				(cls)
				(ego view: 49 loop: 1 cel: 0 setCycle: CycleTo 4 1 self)
			)
			(29
				(Print 53 11 #at -1 20 #width 280 #mode 1 #dispose)
				(= seconds 3)
			)
			(30
				(addToPics add: chest eachElementDo: #init doit:)
				(ego setCycle: EndLoop self)
			)
			(31
				(cls)
				(ego
					view: 0
					ignoreActors: 0
					looper: GradualLooper
					setCycle: StopWalk 2
					setMotion: MoveTo 139 123 self
				)
			)
			(32
				(ego view: 5 setLoop: 0 setCel: 0 setCycle: EndLoop self)
			)
			(33
				(cls)
				(king cycleSpeed: 2 setCycle: EndLoop self)
			)
			(34
				(king
					view: 191
					setLoop: 0
					setCycle: Forward
					moveSpeed: 2
					setMotion: MoveTo 98 118 self
				)
			)
			(35
				(king setMotion: MoveTo 127 127 self)
			)
			(36
				(king
					view: 191
					cycleSpeed: 1
					loop: 1
					cel: 0
					setPri: 9
					setCycle: CycleTo 3 1 self
				)
			)
			(37
				(king cel: 4 setCycle: EndLoop)
				(crown
					init:
					view: 194
					setLoop: 0
					cel: 0
					posn: 160 115
					setCycle: Forward
					setMotion: MoveTo 180 127 self
				)
			)
			(38
				(crown setCel: 0 setCycle: 0)
				(rs1and2Body setCel: 1)
				(rs2Arm stopUpd: hide:)
				(rs2Head hide:)
				(rs1Head hide:)
				(crowd1 setCel: 1)
				(crowd1Head1 hide:)
				(crowd1Head2 hide:)
				(crowd1Head3 hide:)
				(crowd1Head4 hide:)
				(crowd3 setCel: 1)
				(crowd3Head1 hide:)
				(ego setCycle: BegLoop)
				(doctor
					setLoop: 1
					setCycle: Walk
					setMotion: MoveTo 98 112 self
				)
			)
			(39
				(ego view: 2 loop: 2)
				(doctor
					moveSpeed: 0
					setStep: 4 2
					setMotion: MoveTo 127 121 self
				)
			)
			(40
				(doctor setMotion: MoveTo 160 121 self)
			)
			(41
				(cls)
				(doctor setLoop: 2 setStep: 3 2 setCycle: EndLoop)
				(king
					view: 193
					setLoop: 3
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(42
				(talkingHead
					view: 193
					loop: 1
					cel: 0
					description: {king}
					init:
					x: (+ (king x?) 33)
					y: (- (king y?) 9)
					setPri: (+ (king priority?) 1)
					setCycle: Forward
				)
				(= cycles 1)
			)
			(43
				(Print 53 12 #at -1 20 #width 280 #mode 1 #dispose)
				(= seconds 12)
			)
			(44
				(cls)
				(Print 53 13 #at -1 20 #width 280 #mode 1 #dispose)
				(= seconds 6)
			)
			(45
				(cls)
				(talkingHead dispose:)
				(king cycleSpeed: 0 setCycle: BegLoop self)
			)
			(46
				(king setLoop: 0 setCel: 0 setCycle: 0 stopUpd:)
				(= seconds 2)
			)
			(47 (doctor setCycle: BegLoop self))
			(48
				(cls)
				(head
					posn: (+ (doctor x?) 1) (- (doctor y?) 38)
					show:
					setCycle: Forward
				)
				(Print 53 14 #at -1 20 #width 280 #mode 1 #dispose)
				(= seconds 5)
			)
			(49
				(cls)
				(head hide:)
				(doctor
					setLoop: 1
					ignoreActors:
					illegalBits: 0
					setCycle: Walk
					setMotion: MoveTo 173 121 self
				)
				(rs1and2Body setCel: 0)
				(rs2Arm show: setCycle: 0)
				(rs2Head show:)
				(rs1Head show:)
				(crowd1 setCel: 0)
				(crowd1Head1 show:)
				(crowd1Head2 show:)
				(crowd1Head3 show:)
				(crowd1Head4 show:)
				(crowd3 setCel: 0)
				(crowd3Head1 show:)
			)
			(50
				(doctor setMotion: MoveTo 180 127 self)
			)
			(51
				(doctor setLoop: 2 setCycle: EndLoop self)
			)
			(52
				(crown dispose:)
				(doctor setLoop: 3 setCycle: EndLoop self)
			)
			(53
				(ego view: 5 loop: 1 setCycle: EndLoop)
				(doctor
					setLoop: 4
					setCycle: Walk
					setMotion: DPath 173 121 168 123 161 123 self
				)
			)
			(54
				(self setScript: endSongScript)
				(doctor hide:)
				(ego view: 192 loop: 5 cel: 0 setCycle: EndLoop self)
			)
			(55
				(cls)
				(head
					posn: (+ (ego x?) 22) (- (ego y?) 39)
					setLoop: 8
					setCycle: Forward
					show:
				)
				(Print 53 15 #at -1 20 #width 280 #mode 1 #dispose)
				(rs2Arm show: setCycle: Forward)
				(= seconds 4)
			)
			(56
				(cls)
				(head dispose:)
				(Print 53 16 #at -1 20 #width 280 #mode 1 #dispose)
				(= seconds 4)
			)
			(57
				(cls)
				(doctor setLoop: 2 cel: 0 show: setCycle: EndLoop)
				(ego setLoop: 6 setCycle: EndLoop)
				(= seconds 2)
			)
			(58
				(cast eachElementDo: #dispose)
				(curRoom drawPic: 99 8)
				(self cue:)
			)
			(59
				(Print 53 17 #dispose)
				(= register 1)
			)
			(60
				(Print 53 18 #dispose)
				(= register 1)
			)
			(61
				(Print 53 19 #dispose)
				(= register 1)
			)
			(62
				(HandsOn)
				(curRoom newRoom: 85)
				(self dispose:)
			)
		)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if
			(or
				(== (event type?) mouseDown)
				(and
					(== (event type?) keyDown)
					(== (event message?) ENTER)
				)
			)
			(if modelessDialog
				(RedrawCast)
				(modelessDialog dispose:)
				(if seconds (= seconds 0) (= cycles 1))
				(if register (= register 0) (= cycles 1))
			)
			(event claimed: TRUE)
		)
	)
)

(instance crowdTalk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cls)
				(Print 53 20 #at 208 24 #dispose)
				(= seconds 2)
			)
			(1
				(cls)
				(Print 53 21 #at 200 24 #dispose)
				(= seconds 2)
			)
			(2
				(cls)
				(Print 53 22 #at 208 24 #dispose)
				(= seconds 2)
			)
			(3
				(cls)
				(Print 53 23 #at 208 24 #dispose)
				(= seconds 3)
			)
			(4
				(cls)
				(Print 53 24 #at 208 24 #dispose)
				(= seconds 2)
			)
			(5
				(cls)
				(Print 53 25 #at 208 24 #dispose)
				(= seconds 2)
			)
			(6
				(cls)
				(self dispose:)
			)
		)
	)
)

(instance endSongScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 0 23) loop: 1)
				(= cycles 1)
			)
			(1
				((ScriptID 0 23) number: 101 play: self)
			)
			(2
				((ScriptID 0 23) number: 102 play: self)
			)
			(3
				((ScriptID 0 23) number: 103 play: self)
			)
			(4 (self changeState: 2))
		)
	)
)

(instance head of Prop
	(properties
		view 82
	)
)

(instance shield of PicView
	(properties
		x 90
		y 146
		view 553
		loop 2
		signal stopUpdOn
	)
)

(instance chest of PicView
	(properties
		x 120
		y 146
		view 553
	)
)

(instance mirror of PicView
	(properties
		x 60
		y 146
		view 553
		loop 1
	)
)

(instance rs1and2Body of View
	(properties
		x 297
		y 128
		description {royal subject}
		view 255
		signal notUpd
	)
)

(instance rs2Arm of Prop
	(properties
		x 298
		y 97
		description {fan}
		view 255
		loop 1
		priority 10
		signal (| fixPriOn notUpd)
	)
)

(class TurnHead of Prop
	(properties
		description {person's head}
		view 255
		loop 0
		cel 2
		priority 10
		signal (| fixPriOn notUpd)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(
				(and
					(< (+ x 8) (ego x?))
					(< (ego x?) 310)
					(!= cel 0)
				)
				(self setCycle: BegLoop self)
			)
			(
				(and
					(< (- x 8) (ego x?))
					(< (ego x?) (+ x 8))
					(< (ego x?) 310)
					(!= cel 1)
				)
				(= cel 1)
			)
			(
				(and
					(< (ego x?) (- x 8))
					(< (ego x?) 310)
					(!= cel 2)
				)
				(self setCycle: EndLoop self)
			)
		)
	)
	
	(method (cue)
		(self stopUpd:)
	)
)

(instance rs1Head of TurnHead
	(properties
		x 289
		y 86
		loop 2
	)
)

(instance rs2Head of TurnHead
	(properties
		x 305
		y 93
		loop 3
	)
)

(instance crowd1 of View
	(properties
		x 240
		y 136
		description {crowd}
		view 255
		loop 4
		signal notUpd
	)
)

(instance crowd1Head1 of TurnHead
	(properties
		x 210
		y 93
		loop 5
	)
)

(instance crowd1Head2 of TurnHead
	(properties
		x 272
		y 99
		loop 8
	)
)

(instance crowd1Head3 of TurnHead
	(properties
		x 250
		y 92
		loop 7
	)
)

(instance crowd1Head4 of TurnHead
	(properties
		x 233
		y 98
		loop 6
	)
)

(instance crowd2 of PicView
	(properties
		x 253
		y 185
		description {crowd}
		view 253
	)
)

(instance crowd2Part1 of PicView
	(properties
		x 303
		y 175
		description {crowd}
		view 253
		cel 1
	)
)

(instance crowd2Head1 of TurnHead
	(properties
		x 244
		y 147
		loop 11
		priority 15
		signal (| ignrAct fixPriOn notUpd)
	)
)

(instance crowd2Head2 of TurnHead
	(properties
		x 263
		y 144
		loop 12
		priority 14
		signal (| ignrAct fixPriOn notUpd)
	)
)

(instance crowd2Head3 of TurnHead
	(properties
		x 303
		y 138
		loop 13
		priority 13
		signal (| ignrAct fixPriOn notUpd)
	)
)

(instance crowd3 of View
	(properties
		x 264
		y 122
		description {crowd}
		view 255
		loop 9
		signal notUpd
	)
)

(instance crowd3Head1 of TurnHead
	(properties
		x 266
		y 83
		loop 10
	)
)

(instance gramHead of Prop
	(properties
		x 136
		y 95
		view 5
		loop 2
		signal notUpd
	)
	
	(method (doVerb)
	)
)

(instance king of Actor
	(properties
		x 81
		y 118
		view 190
		loop 2
		cel 2
		priority 7
		signal (| fixPriOn notUpd)
	)
)

(instance talkingHead of Prop
	(properties
		x 65
		y 88
		description {king}
		view 190
		loop 1
	)
	
	(method (doVerb)
	)
)

(instance crown of Actor
	(properties)
)

(instance doctor of Actor
	(properties
		x 94
		y 112
		view 192
		signal fixPriOn
	)
)
