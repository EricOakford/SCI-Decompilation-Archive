;;; Sierra Script 1.0 - (do not remove this comment)
(script# 212)
(include game.sh)
(use Main)
(use CycleBet)
(use BalloonTalker)
(use TWRoom)
(use Polygon)
(use Feature)
(use LoadMany)
(use Sound)
(use Jump)
(use Motion)
(use Actor)
(use System)

(public
	rm212 0
	watchManTalker 1
	pughTalker 13
	percyTalker 14
)

(local
	local0
)
(instance rm212 of ADRoom
	(properties
		picture 210
	)
	
	(method (init)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PContainedAccess
					init:
						207 92
						230 99
						243 111
						264 110
						264 102
						222 86
						206 92
					yourself:
				)
		)
		(super init: &rest)
		(door init: ignoreActors: TRUE addToPic:)
		(woodThing init: ignoreActors: TRUE addToPic:)
		(walkHandler add: barrels curRoom)
		(barrels init:)
		(switch prevRoomNum
			(610
				(theMusic number: 216 setLoop: -1 play:)
				(watchMan
					view: 815
					setLoop: 3
					setCycle: Walk
					x: 300
					y: 110
					init:
				)
				(horse init: ignoreActors: TRUE addToPic:)
				(driver setPri: 13 init: ignoreActors: TRUE addToPic:)
				(carriage
					view: 224
					setLoop: 0
					setCel: 0
					x: 168
					y: 152
					init:
					ignoreActors: TRUE
					addToPic:
				)
				(imaWench view: 2008 x: 260 y: 155 init: stopUpd:)
				(genPugh view: 2007 x: 168 y: 179 init: stopUpd:)
				(percy view: 228 setLoop: 2 init: stopUpd:)
				((ScriptID 895 1)
					x: 285
					y: 155
					init:
					actions: ljActions
					view: 217
					setLoop: 5
					cel: 5
					stopUpd:
				)
				(watchManTalker x: 95 y: 90 tailPosn: 4)
				((ScriptID 2018 1) x: 130 y: 115 tailPosn: 1)
				(self setScript: abductionScam)
			)
			(200
				((ScriptID 895 0)
					x: 249
					y: 109
					init:
					normalize:
					view: 220
					setLoop: 4
					setCel: 0
				)
				(theMusic number: 213 setLoop: 1 play:)
				(self setScript: pepperBummed)
			)
			(else 
				(watchMan view: 815 setLoop: 2 x: 162 y: 142 init:)
				(self setScript: abductionScam)
			)
		)
	)
	
	(method (dispose)
		(walkHandler delete: barrels curRoom)
		((ScriptID 895 1) actions: 0)
		(LoadMany FALSE FOLLOW)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_WALK
				(abductionScam seconds: 1)
			)
			(V_LOOK
				(super doVerb: theVerb)
			)
			(else 
				(abductionScam seconds: 1)
			)
		)
	)
	
	(method (cue)
		((ScriptID 895 1) setHeading: 90)
	)
)

(instance pepperBummed of Script
	(properties)
	
	(method (doit)
		(if
			(and
				(== (theMusic prevSignal?) -1)
				(== (self state?) 3)
			)
			(self cue:)
		)
		(super doit: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 895 0) setCycle: EndLoop self)
			)
			(1
				((ScriptID 895 0) setCycle: BegLoop self)
			)
			(2
				(messager say: 1 0 3 12 self)
			)
			(3)
			(4 (messager say: 1 7 5 0 self))
			(5 (curRoom newRoom: 600))
		)
	)
)

(instance abductionScam of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (== prevRoomNum 610)
					((ScriptID 895 0) x: 249 y: 109 init: normalize:)
					(= state 49)
					(self cue:)
				else
					((ScriptID 895 0)
						view: 217
						x: 197
						y: 90
						init:
						view: 217
						setLoop: 0
						setCycle: Walk
						setMotion: MoveTo 249 109 self
					)
					((ScriptID 895 1) x: 250 y: 100)
				)
			)
			(1
				((ScriptID 2000 3)
					winX: 88
					winY: 93
					talkWidth: 180
					tailPosn: 4
				)
				((ScriptID 895 0) setCel: 5 stopUpd:)
				(messager say: 1 0 1 1 self)
			)
			(2
				(theMusic number: 210 setLoop: -1 play:)
				(watchMan setCycle: Walk setMotion: MoveTo 204 118 self)
			)
			(3
				((ScriptID 2004 0) winX: 141 winY: 107 tailPosn: 4)
				(messager say: 1 0 1 2 10 self)
			)
			(4
				(watchMan setMotion: MoveTo 325 110 self)
			)
			(5
				(theMusic fade:)
				(watchMan dispose:)
				(= ticks 10)
			)
			(6 (messager say: 1 0 2 1 self))
			(7
				(fourth
					init:
					setCycle: Walk
					setLoop: 0
					setMotion: MoveTo 208 169 self
				)
				(theMusic2 number: 919 setLoop: 1 play:)
			)
			(8
				(fourth setScript: catRuns)
				(= seconds 5)
			)
			(9
				((ScriptID 2004 0) winX: 151 winY: 117 tailPosn: 4)
				(theMusic2 number: 900 setLoop: 1 play:)
				(messager say: 1 0 2 2 self)
			)
			(10
				((ScriptID 895 0)
					view: 217
					setPri: 7
					setLoop: 1
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(11
				((ScriptID 895 0) view: 791 setPri: -1 stopUpd:)
				((ScriptID 895 1)
					x: 248
					y: 110
					view: 217
					setLoop: 4
					setCel: 0
					ignoreActors: 1
					init:
					actions: ljActions
					setCycle: EndLoop self
				)
			)
			(12
				(messager say: 1 0 2 3 self)
			)
			(13
				((ScriptID 895 1)
					normalize:
					setMotion: MoveTo 231 135 self
				)
				(catRuns dispose:)
				(fourth setLoop: 1 setMotion: MoveTo 250 169 self)
			)
			(14)
			(15
				(theMusic2 number: 2109 setLoop: 1 play: self)
			)
			(16
				(fourth setMotion: 0 setCycle: 0 setLoop: 3 setCel: 0)
				(= seconds 2)
			)
			(17
				(theMusic2 number: 953 setLoop: 1 play:)
				(fourth setCycle: EndLoop self)
			)
			(18
				(fourth setStep: 7 4 setMotion: MoveTo 420 179)
				(= seconds 2)
			)
			(19
				(theMusic2 number: 953 setLoop: 1 play:)
				((ScriptID 895 1)
					view: 226
					x: 278
					y: 165
					setLoop: 2
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(20
				((ScriptID 895 1)
					setStep: 7 7
					setMotion: MoveTo 410 179 self
				)
			)
			(21
				(fourth dispose:)
				((ScriptID 895 1) dispose:)
				(carriage
					init:
					illegalBits: 0
					setLoop: 0
					setCycle: Walk
					setPri: 12
					setMotion: MoveTo 165 158 self
				)
				(theMusic number: 214 setLoop: 1 play: 0 fade: 127 10 5 0)
			)
			(22
				(carriage
					view: 224
					setLoop: 0
					setCel: 0
					x: 168
					y: 152
					addToPic:
				)
				(horse init: addToPic:)
				(driver init: stopUpd:)
				(fourth
					init:
					setLoop: 4
					setCycle: Walk
					setMotion: MoveTo 320 158 self
				)
			)
			(23
				((ScriptID 895 1)
					init:
					actions: ljActions
					setStep: 5 4
					setPri: 14
				)
				(= ticks 120)
				(fourth setMotion: MoveTo 35 158 self)
			)
			(24
				(theMusic2 number: 2109 setLoop: 1 play:)
			)
			(25
				(theMusic stop:)
				(theMusic2 number: 2108 setLoop: 1 play:)
				(= ticks 60)
			)
			(26
				(fourth setMotion: MoveTo -35 158)
				((ScriptID 895 1)
					x: 168
					y: 135
					view: 217
					setLoop: 4
					cel: 0
					setCycle: CycleTo 5 1 self
				)
			)
			(27
				(fourth dispose:)
				(theMusic2 number: 2110 setLoop: 1 play:)
				((ScriptID 895 1)
					setCycle: EndLoop
					setMotion: JumpTo 240 158 self
				)
			)
			(28
				((ScriptID 895 1) setMotion: JumpTo 285 155 self)
			)
			(29
				((ScriptID 895 1) setLoop: 5 setCycle: EndLoop self)
			)
			(30
				(sfx number: 2111 setLoop: -1 play:)
				((ScriptID 895 1) setCycle: CycleBet 4 6 -1)
				(driver setCycle: EndLoop self)
			)
			(31
				(carDoor init: setCycle: EndLoop self)
				(theMusic2 number: 2113 setLoop: 1 play:)
			)
			(32
				(driver addToPic:)
				(carDoor addToPic:)
				(imaWench init: setCycle: EndLoop self)
			)
			(33
				(imaWench
					view: 832
					setCycle: Walk
					setMotion: MoveTo 260 155 self
				)
			)
			(34
				(imaWench view: 228 setLoop: 0 setCel: 0 stopUpd:)
				(genPugh init: setCel: 0 setCycle: EndLoop self)
			)
			(35
				(genPugh
					view: 818
					setCycle: Walk
					setMotion: MoveTo 168 179 self
				)
			)
			(36
				(genPugh view: 228 setLoop: 1 stopUpd:)
				(percy init: setCel: 0 setCycle: EndLoop self)
				(theMusic2 number: 2115 setLoop: 1 play:)
			)
			(37 (= seconds 3))
			(38
				(sfx stop:)
				(theMusic number: 216 setLoop: -1 play:)
				(percy view: 218 setLoop: 3 cel: 0 setCycle: EndLoop self)
			)
			(39
				(percy view: 228 setLoop: 2 stopUpd:)
				(watchMan
					init:
					setLoop: 3
					setCycle: Walk
					setMotion: MoveTo 300 110 self
				)
			)
			(40
				(watchMan stopUpd:)
				(watchManTalker x: 95 y: 90 tailPosn: 4)
				((ScriptID 2018 1) x: 130 y: 115 tailPosn: 1)
				(genPugh view: 2007 setCycle: Forward)
				(messager say: 1 0 2 4 self)
			)
			(41
				(genPugh view: 228 setLoop: 1 setCycle: 0 stopUpd:)
				(percy view: 2005 setCycle: Forward)
				(messager say: 1 0 2 5 self)
			)
			(42
				(percy view: 228 setLoop: 2 setCycle: 0 stopUpd:)
				(imaWench view: 2008 setCycle: Forward)
				(messager say: 1 0 2 6 self)
			)
			(43
				(imaWench view: 228 setLoop: 0 setCycle: 0 stopUpd:)
				(messager say: 1 0 2 7 self)
			)
			(44
				(percy view: 2005 setCycle: Forward)
				(messager say: 1 0 2 8 self)
			)
			(45
				(percy view: 228 setLoop: 2 setCycle: 0 stopUpd:)
				(messager say: 1 0 2 9 self)
			)
			(46
				(percy view: 2005 setCycle: Forward)
				(messager say: 1 0 2 10 self)
			)
			(47
				(percy view: 228 setLoop: 2 setCycle: 0 stopUpd:)
				(messager say: 1 0 2 11 self)
			)
			(48
				(percy view: 2005 setCycle: Forward)
				(messager say: 1 0 2 12 self)
			)
			(49
				(percy view: 228 setLoop: 2 setCycle: 0 stopUpd:)
				((ScriptID 2004 0) winX: 160 winY: 140 tailPosn: 1)
				(messager say: 1 0 2 13 self)
			)
			(50
				(messager say: 1 0 2 14 self)
			)
			(51
				(percy view: 2005 setCycle: Forward)
				(messager say: 1 0 2 15 self)
			)
			(52
				(percy view: 228 setLoop: 2 setCycle: 0 stopUpd:)
				(messager say: 1 0 2 16 self)
			)
			(53
				(messager say: 1 0 2 17 self)
			)
			(54
				(theGame handsOn:)
				((ScriptID 895 0) normalize: setHeading: 180)
				(= ticks 10)
			)
			(55
				(SetCursor 160 0)
				(= seconds 15)
			)
			(56
				(if (not local0)
					(messager say: 1 0 4 0 self)
				else
					(self cue:)
				)
			)
			(57
				(if (not local0)
					(= global199 1)
					(curRoom newRoom: 610)
				else
					(self cue:)
				)
			)
			(58
				(messager say: 1 0 3 1 self)
			)
			(59
				(genPugh view: 2007 setCycle: Forward)
				(messager say: 1 0 3 2 self)
			)
			(60
				(genPugh view: 228 setLoop: 1 setCycle: 0 stopUpd:)
				(messager say: 1 0 3 3 self)
			)
			(61
				(watchMan setLoop: 2 setMotion: MoveTo 325 110 self)
			)
			(62
				(watchMan dispose:)
				(imaWench view: 2008 setCycle: Forward)
				(messager say: 1 0 3 4 self)
			)
			(63
				(imaWench view: 228 setLoop: 0 setCycle: 0 stopUpd:)
				(genPugh view: 2007 setCycle: Forward)
				(messager say: 1 0 3 5 self)
			)
			(64
				(genPugh view: 228 setLoop: 1 setCycle: 0 stopUpd:)
				(imaWench view: 2008 setCycle: Forward)
				(messager say: 1 0 3 6 self)
			)
			(65
				(imaWench view: 228 setLoop: 0 setCycle: 0 stopUpd:)
				(genPugh view: 2007 setCycle: Forward)
				(messager say: 1 0 3 7 self)
			)
			(66
				(genPugh view: 228 setLoop: 1 setCycle: 0 stopUpd:)
				(imaWench
					view: 227
					setLoop: 2
					setPri: 14
					setCycle: EndLoop self
				)
			)
			(67
				(imaWench setCycle: CycleBet 7 10 -1)
				(messager say: 1 0 3 8 9 self)
			)
			(68
				(genPugh
					view: 227
					setLoop: 3
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(69
				(genPugh setCycle: CycleBet 7 4 1 self)
			)
			(70
				(genPugh setCycle: BegLoop self)
			)
			(71
				(genPugh view: 2007 setCycle: Forward)
				(messager say: 1 0 3 10 self)
			)
			(72
				(genPugh view: 228 setLoop: 1 stopUpd:)
				(percy view: 2005 setCycle: Forward)
				(messager say: 1 0 3 11 self)
			)
			(73
				(percy view: 228 setLoop: 2 stopUpd:)
				(imaWench setCycle: BegLoop self)
			)
			(74
				(imaWench
					view: 220
					setLoop: 0
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(75 (= seconds 2))
			(76
				(theMusic fade:)
				(curRoom newRoom: 200)
			)
		)
	)
)

(instance pepperHides of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff: points: 215 3)
				(= local0 1)
				((ScriptID 895 0)
					normalize:
					setCycle: Walk
					setMotion: MoveTo 217 99 self
				)
			)
			(1
				((ScriptID 895 0) setHeading: 180)
				(= ticks 20)
			)
			(2
				(abductionScam seconds: 1)
				(self dispose:)
			)
		)
	)
)

(instance catRuns of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic2 number: 919 setLoop: 1 play:)
				(fourth setLoop: 1 setMotion: MoveTo 293 169 self)
			)
			(1
				(theMusic2 number: 919 setLoop: 1 play:)
				(fourth setLoop: 0 setMotion: MoveTo 208 169 self)
			)
			(2 (self init:))
		)
	)
)

(instance carriage of Actor
	(properties
		x 500
		y 76
		view 222
		signal $4000
	)
	
	(method (doVerb theVerb)
		(curRoom doVerb: theVerb)
	)
)

(instance watchMan of Actor
	(properties
		noun 3
		signal $4800
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 84)
			(theMusic2 number: 927 setLoop: 1 play:)
			(super doVerb: theVerb)
		else
			(curRoom doVerb: theVerb)
		)
	)
)

(instance fourth of Actor
	(properties
		x 380
		y 169
		view 226
		loop 1
		signal $4000
		illegalBits $0000
	)
)

(instance imaWench of Actor
	(properties
		x 204
		y 150
		view 219
		loop 2
		priority 13
		signal $4010
	)
	
	(method (doVerb theVerb)
		(curRoom doVerb: theVerb)
	)
)

(instance genPugh of Actor
	(properties
		x 202
		y 148
		view 219
		priority 13
		signal $4010
	)
	
	(method (doVerb theVerb)
		(curRoom doVerb: theVerb)
	)
)

(instance percy of Actor
	(properties
		x 209
		y 143
		view 219
		loop 1
		priority 13
		signal $4010
	)
	
	(method (doVerb theVerb)
		(curRoom doVerb: theVerb)
	)
)

(instance horse of Prop
	(properties
		x 108
		y 161
		view 218
		loop 1
	)
	
	(method (doVerb theVerb)
		(curRoom doVerb: theVerb)
	)
)

(instance driver of Prop
	(properties
		x 144
		y 192
		z 80
		view 218
		loop 2
		priority 12
		signal $0010
	)
	
	(method (doVerb theVerb)
		(curRoom doVerb: theVerb)
	)
)

(instance carDoor of Prop
	(properties
		x 179
		y 197
		z 70
		view 225
		priority 12
		signal $4010
	)
	
	(method (doVerb theVerb)
		(curRoom doVerb: theVerb)
	)
)

(instance door of View
	(properties
		x 139
		y 50
		view 210
		loop 1
	)
	
	(method (doVerb theVerb)
		(curRoom doVerb: theVerb)
	)
)

(instance woodThing of View
	(properties
		x 38
		y 96
		view 210
		cel 2
		priority 5
		signal $6811
	)
	
	(method (doVerb theVerb)
		(curRoom doVerb: theVerb)
	)
)

(instance barrels of Feature
	(properties
		x 218
		y 190
		noun 2
		nsTop 47
		nsLeft 206
		nsBottom 99
		nsRight 242
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(7
				((ScriptID 895 0) setScript: pepperHides)
			)
			(3
				((ScriptID 895 0) setScript: pepperHides)
			)
			(else 
				(curRoom doVerb: theVerb)
			)
		)
	)
)

(instance watchManTalker of BalloonTalker
	(properties
		x 30
		y 75
		talkWidth 180
		tailPosn 1
	)
	
	(method (say)
		(watchMan stopUpd:)
		(super say: &rest)
	)
)

(instance pughTalker of BalloonTalker
	(properties
		x 5
		y 120
		talkWidth 160
		tailPosn 1
	)
)

(instance percyTalker of BalloonTalker
	(properties
		x 33
		y 105
		talkWidth 160
		tailPosn 1
	)
)

(instance sfx of Sound)

(instance ljActions of Actions

	(method (doVerb theVerb)
		(return
			(switch theVerb
				(6 (super doVerb: theVerb))
				(84 (super doVerb: theVerb))
				(else 
					(abductionScam seconds: 1)
					(return 1)
				)
			)
		)
	)
)
