;;; Sierra Script 1.0 - (do not remove this comment)
(script# 214)
(include game.sh)
(use Main)
(use ExitFeature)
(use OccasionalCycle)
(use BalloonTalker)
(use TWInvItem)
(use TWRoom)
(use Print)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)
(use System)

(public
	rm214 0
	watchManTalker 1
	poorRichardTalker 4
	hari1Talker 5
	hari2Talker 6
)

(local
	local0
	gabbedPR
	local2 =  1
	TalkPR2 =  1
	local4 =  1
	local5 =  1
)
(instance rm214 of ADRoom
	(properties
		noun 15
		picture 210
	)
	
	(method (init)
		(if (== gameAct 0) (= gameAct 1))
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init: 319 100 262 102 177 87 177 0 319 0
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 152 136 109 140 30 160 0 145 0 0 168 0 173 89 262 104 196 116
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 83 188 83 178 135 178 135 188
					yourself:
				)
		)
		(narrator x: 100 y: 25)
		(= global114 1)
		(theMusic fade:)
		(ego
			view: 209
			setLoop: 0
			setCycle: 0
			setScale: 0
			x: 101
			y: -10
			setPri: 14
			put: 0
			signal: 26640
			init:
		)
		(hari
			setCycle: Walk
			x: 203
			y: 142
			setLoop: 0
			setMotion: MoveTo 170 152
			init:
		)
		(self setScript: sFallIn)
		(super init: &rest)
		(woodThing init: addToPic:)
		(poorRichard
			x: 67
			y: 139
			setCycle: OccasionalCycle self 1 10 200
			init:
		)
		(dipper approachVerbs: 7 init: stopUpd:)
		(door init: addToPic:)
		(alleyEnter init:)
		(barrel init:)
		(bigBuilding init:)
		(crates init: setOnMeCheck: 1 16384)
		(hills init: setOnMeCheck: 1 8192)
		(idiotSign init:)
		(stocks init:)
		(shedDoor init:)
		(shedLock init:)
		(shed init: setOnMeCheck: 1 4096)
		(shedWindow init:)
		(smallBuild init:)
		(eastEFeature init:)
		(westEFeature init:)
		(alley init:)
		(eastRoad init: setOnMeCheck: 1 8)
		(westRoad init: setOnMeCheck: 1 4)
		(inventory
			addAfter: (inventory at: 5) (Dipper init: yourself:)
		)
	)
	
	(method (doit)
		(cond 
			(script)
			(
				(and
					(cast contains: poorRichard)
					(== (ego edgeHit?) 2)
				)
				(curRoom setScript: cantExitEast)
			)
			(
			(and (cast contains: poorRichard) (< (ego x?) 5)) (curRoom setScript: cantExitWest))
			(
				(and
					(cast contains: poorRichard)
					(& (ego onControl: 1) $0002)
				)
				(curRoom setScript: cantExitWest)
			)
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(inventory delete: Dipper)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(= global215 16)
		(if (== theVerb 999) (= theVerb 88))
		(super doVerb: theVerb)
	)
	
	(method (cue)
		((ScriptID 895 1) setHeading: 90)
	)
)

(instance cantExitWest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(randomRichard dispose:)
				(messager say: 20 0 22 0 self)
			)
			(1
				((ScriptID 895 0)
					setMotion:
						MoveTo
						(+ ((ScriptID 895 0) x?) 30)
						((ScriptID 895 0) y?)
						self
				)
			)
			(2
				(theGame handsOn:)
				(if (not gabbedPR)
					(poorRichard setScript: randomRichard)
				)
				(self dispose:)
			)
		)
	)
)

(instance cantExitEast of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(randomRichard dispose:)
				(messager say: 20 0 22 0 self)
			)
			(1
				((ScriptID 895 0)
					setMotion:
						MoveTo
						(- ((ScriptID 895 0) x?) 30)
						((ScriptID 895 0) y?)
						self
				)
			)
			(2
				(theGame handsOn:)
				(if (not gabbedPR)
					(poorRichard setScript: randomRichard)
				)
				(self dispose:)
			)
		)
	)
)

(instance randomRichard of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 5 15)))
			(1
				(if modelessDialog
					(self init:)
				else
					(= state -1)
					((ScriptID 895 0) stopUpd:)
					(switch (++ local0)
						(1
							(messager say: 20 0 23 0 self)
						)
						(2
							(messager say: 20 0 24 0 self)
						)
						(3
							(messager say: 20 0 25 0 self)
						)
						(else 
							(messager say: 20 0 26 0 self)
						)
					)
				)
			)
		)
	)
)

(instance sTalkToPoorRichard of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(randomRichard dispose:)
				(++ gabbedPR)
				(cond 
					(local2 (theGame points: 209 1))
					(TalkPR2 (theGame points: 210 1))
				)
				(= cycles 1)
			)
			(1
				(cond 
					((and (< gabbedPR 1) (Btst 213))
						(= global215 10)
						(Printf {TalkPR2 = %d} TalkPR2)
						(Printf {gabbedPR = %d} gabbedPR)
						(messager say: 14 85 9 0 self)
					)
					(local2 (messager say: 14 85 10 0 self))
					(TalkPR2 (= global215 39) (messager say: 14 85 12 1 3 self))
					(local4 (= local4 0) (messager say: 14 85 13 0 self))
					(else (messager say: 14 85 11 0 self))
				)
			)
			(2
				(if (and (not local2) TalkPR2)
					(= global215 38)
					(messager say: 14 85 12 4 5 self)
				else
					(self cue:)
				)
			)
			(3
				(if (and (not local2) TalkPR2)
					(= TalkPR2 0)
					(messager say: 14 85 12 6 self)
				else
					(= local2 0)
					(self cue:)
				)
			)
			(4
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sFallIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(poorRichard setCycle: 0 stopUpd:)
				(= cycles 2)
			)
			(1
				(messager say: 20 0 34 0 self)
			)
			(2
				(= register ((ScriptID 895 0) cycleSpeed?))
				(theMusic2 number: 2121 loop: 1 play:)
				((ScriptID 895 0)
					view: 223
					setPri: -1
					cel: 1
					x: 116
					y: 7
				)
				(= ticks 5)
			)
			(3
				((ScriptID 895 0) cel: 2 x: 131 y: 20)
				(= ticks 5)
			)
			(4
				((ScriptID 895 0) cel: 3 x: 138 y: 37)
				(= ticks 5)
			)
			(5
				((ScriptID 895 0) hide:)
				(hari view: 223 setLoop: 1 cel: 0 setCycle: 0)
				(= cycles 1)
			)
			(6
				(theMusic2 number: 2100 loop: 1 play:)
				(ShakeScreen 3 1)
				(= cycles 2)
			)
			(7 (hari setCycle: EndLoop self))
			(8
				(theMusic2 number: 2126 play:)
				(hari view: 211 setLoop: 0 cel: 0 setCycle: 0)
				((ScriptID 895 0)
					show:
					view: 223
					setLoop: 0
					setPri: 12
					cel: 0
					x: 167
					y: 109
					cycleSpeed: 8
					setCycle: CycleTo 1 1 self
					moveSpeed: 0
					setStep: 5 4
					setMotion: MoveTo 172 107 self
				)
			)
			(9)
			(10
				((ScriptID 895 0)
					setCycle: CycleTo 2 1 self
					setMotion: MoveTo 168 102 self
				)
			)
			(11)
			(12
				((ScriptID 895 0)
					setCycle: CycleTo 4 1 self
					setMotion: MoveTo 145 88 self
				)
			)
			(13)
			(14
				((ScriptID 895 0)
					setCycle: CycleTo 5 1 self
					setMotion: MoveTo 132 88 self
				)
			)
			(15)
			(16
				((ScriptID 895 0)
					setCycle: CycleTo 6 1 self
					setMotion: MoveTo 118 95 self
				)
			)
			(17)
			(18
				((ScriptID 895 0)
					setCycle: CycleTo 7 1 self
					setMotion: MoveTo 94 106 self
				)
			)
			(19)
			(20
				((ScriptID 895 0)
					setCycle: CycleTo 8 1 self
					setMotion: MoveTo 88 121 self
				)
			)
			(21)
			(22
				((ScriptID 895 0)
					setCycle: CycleTo 9 1 self
					setMotion: MoveTo 92 143 self
				)
			)
			(23)
			(24
				((ScriptID 895 0)
					setCycle: EndLoop self
					setMotion: MoveTo 92 153 self
				)
			)
			(25)
			(26
				(theMusic2 number: 2122 play:)
				(ShakeScreen 3 1)
				(= seconds 3)
			)
			(27
				((ScriptID 895 0)
					view: 209
					setLoop: 3
					cel: 0
					x: 124
					y: 142
					setCycle: EndLoop self
				)
			)
			(28
				((ScriptID 895 0)
					normalize:
					x: 123
					y: 140
					setHeading: 125
				)
				(= cycles 2)
			)
			(29
				(bell
					view: 199
					init:
					setLoop: 0
					x: (- (hari x?) 15)
					y: (- (hari y?) 31)
					setPri: 15
					ignoreActors: 1
					setCycle: EndLoop self
				)
				(theMusic2 number: 2125 loop: 1 play:)
			)
			(30
				((ScriptID 895 1)
					init:
					approachVerbs: 7
					view: 209
					setLoop: 1
					setCycle: 0
					setScale: 0
					approachX: 116
					approachY: 172
					x: 64
					y: -2
					setStep: 20 20
					cycleSpeed: 1
					moveSpeed: 1
					normal: 0
				)
				(self setScript: sFallInLock self)
			)
			(31
				(bell dispose:)
				(hari view: 199 setLoop: 1 cel: 1)
				((ScriptID 895 0) stopUpd:)
				(hari stopUpd:)
				(= ticks 30)
				(theMusic number: 212 setLoop: -1 play:)
			)
			(32
				(messager say: 20 0 19 0 self)
			)
			(33
				((ScriptID 895 0) startUpd:)
				(hari
					forceUpd:
					view: 211
					setLoop: 1
					setCycle: Walk
					setMotion: MoveTo 119 175 self
				)
			)
			(34
				(hari stopUpd:)
				((ScriptID 895 0) stopUpd:)
				((ScriptID 895 1) stopUpd:)
				(poorRichard setCycle: 0 stopUpd:)
				(self setScript: fakeTrivia self)
			)
			(35
				((ScriptID 895 0) setHeading: 200)
				(hari setMotion: MoveTo 46 180 self)
			)
			(36
				((ScriptID 895 0) setHeading: 245)
				(hari setMotion: MoveTo 0 170 self)
			)
			(37
				(hari dispose:)
				(= seconds 2)
			)
			(38
				(theMusic fade:)
				((ScriptID 895 0) stopUpd:)
				(messager say: 20 0 20 0 self)
			)
			(39
				((ScriptID 895 0) setMotion: MoveTo 102 142 self)
			)
			(40
				(theGame handsOn:)
				(poorRichard
					setCycle: OccasionalCycle self 1 10 200
					setScript: randomRichard
				)
				(self dispose:)
			)
		)
	)
)

(instance sFallInLock of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic2 number: 2123 loop: 1 play:)
				((ScriptID 895 1)
					setCycle: Forward
					setPri: 15
					ignoreActors: 1
					setMotion: MoveTo 107 175 self
				)
			)
			(1
				(ShakeScreen 3 1)
				((ScriptID 895 1) setLoop: 2 cel: 0 x: 101 y: 177)
				(theMusic2 number: 2124 play:)
				(= ticks 10)
			)
			(2
				((ScriptID 895 1) setCycle: CycleTo 4 1 self)
			)
			(3
				((ScriptID 895 1) setCycle: EndLoop self)
			)
			(4
				((ScriptID 895 1) setLoop: 4 cel: 0 setCycle: EndLoop self)
			)
			(5
				(hole init: addToPic:)
				(= seconds 4)
			)
			(6
				(theMusic2 number: 905 setLoop: 1 play: self)
			)
			(7
				((ScriptID 895 1)
					normalize:
					x: 102
					y: 174
					setMotion: MoveTo 106 187 self
				)
			)
			(8
				((ScriptID 895 1) setHeading: 90)
				(self dispose:)
			)
		)
	)
)

(instance sFreePoorRichard of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset 32)
				(randomRichard dispose:)
				(theGame points: 214 2)
				(ego setMotion: PolyPath 49 150 self)
			)
			(1
				(ego
					view: 212
					setLoop: 2
					cel: 0
					x: 66
					y: 142
					setCycle: 0
					setPri: 6
				)
				(poorRichard hide:)
				(= ticks 30)
			)
			(2
				(ego setCycle: EndLoop self)
				(theMusic2 number: 2103 loop: 1 play:)
			)
			(3
				(messager say: 22 7 32 0 self)
			)
			(4
				(messager say: 20 0 18 0 self)
			)
			(5
				(poorRichard
					view: 830
					setLoop: 1
					setCycle: Forward
					ignoreActors: 1
					illegalBits: 0
					x: 59
					y: 139
					setPri: 4
					setMotion: MoveTo -20 135 self
					show:
				)
				(ego
					normalize:
					x: 49
					y: 150
					setMotion: MoveTo 143 155 self
				)
			)
			(6)
			(7
				(if (ego has: 6)
					(self setScript: putDipper self)
				else
					(self cue:)
				)
			)
			(8
				(if (ego has: 6)
					(ego put: 6)
					(messager say: 20 0 16 0 self)
				else
					(self cue:)
				)
			)
			(9
				(dipper addToPic:)
				((ScriptID 895 0) setHeading: 90)
				(theMusic number: 210 loop: -1 play:)
				(watchMan
					view: 815
					init:
					setLoop: 3
					x: 312
					y: 109
					setCycle: Walk
					setMotion: MoveTo 113 146 self
				)
				((ScriptID 895 1) normal: 0)
			)
			(10
				((ScriptID 895 0) setHeading: 270)
				(watchMan setLoop: 2)
				(= ticks 30)
			)
			(11
				(= global215 53)
				(messager say: 20 0 27 0 self)
			)
			(12
				(ego hide:)
				(theMusic stop:)
				(watchMan
					view: 213
					setLoop: 1
					setCel: 0
					posn: 121 157
					setCycle: CycleTo 5 1 self
				)
				((ScriptID 895 1)
					normalize:
					setCycle: Walk
					setMotion: PolyPath 47 156 curRoom
				)
				(= register 8)
			)
			(13
				(if (!= (theMusic2 number?) 2101)
					(theMusic2 number: 2101 setLoop: -1 play:)
				)
				(watchMan cel: 6)
				(= ticks 20)
			)
			(14
				(watchMan cel: 5)
				(if (-- register)
					(= state (- state 2))
				else
					(= register 5)
				)
				(= ticks 20)
			)
			(15
				(watchMan cel: 7 setCycle: EndLoop self)
				(theMusic2 number: 2102 setLoop: 1 play: self)
			)
			(16)
			(17
				(if (-- register) (= state (- state 3)))
				(= ticks 10)
			)
			(18
				(messager say: 20 0 28 0 self)
			)
			(19
				(theMusic2 number: 905 setLoop: 1 play: self)
			)
			(20
				((ScriptID 895 1)
					view: 213
					setLoop: 3
					posn: 121 155
					ignoreActors: 1
					illegalBits: 0
					setCycle: CycleTo 5 1 self
				)
			)
			(21
				(watchMan hide: forceUpd:)
				((ScriptID 895 1) cel: 6)
				(theMusic2 number: 902 loop: 1 play: self)
			)
			(22
				((ScriptID 895 1) setCycle: EndLoop self)
				(theMusic2 number: 2105 play:)
			)
			(23
				(messager say: 20 0 29 0 self)
			)
			(24 (= seconds 3))
			(25
				(theMusic fade:)
				(curRoom newRoom: 240)
			)
		)
	)
)

(instance putDipper of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(randomRichard dispose:)
				(if register
					(messager say: 8 7 0 0 self)
				else
					((ScriptID 895 0) setMotion: PolyPath 143 132 self)
				)
			)
			(1
				((ScriptID 895 0)
					view: 212
					setLoop: 1
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(2
				(if register
					(self cue:)
				else
					((ScriptID 895 0)
						normalize:
						setCycle: Walk
						setMotion: PolyPath 143 155 self
					)
				)
			)
			(3
				(if register (= register 0))
				((ScriptID 895 0) normalize:)
				(self dispose:)
			)
		)
	)
)

(instance fakeTrivia of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Cursor showCursor: 0)
				(theIconBar disable:)
				(ourCursor
					init:
					illegalBits: 0
					ignoreActors: 1
					ignoreHorizon: 1
					setSpeed: 0
					setStep: 5 7
					setMotion: MoveTo 266 17 self
				)
			)
			(1
				(theWalkIcon init: stopUpd:)
				(theLookIcon init: stopUpd:)
				(theHandIcon init: stopUpd:)
				(theTalkIcon init: stopUpd:)
				(theInvIcon init: stopUpd:)
				(theCurInvIcon init: stopUpd:)
				(theControlIcon init: stopUpd:)
				(qMarkIcon init: stopUpd:)
				(truthIcon init: stopUpd:)
				(quizIcon init: stopUpd:)
				(= seconds 2)
			)
			(2
				(truthIcon setCel: 1)
				(= seconds 2)
			)
			(3
				(truthIcon setCel: 0)
				(= seconds 2)
			)
			(4
				(theWalkIcon dispose:)
				(theLookIcon dispose:)
				(theTalkIcon dispose:)
				(theHandIcon dispose:)
				(theInvIcon dispose:)
				(theCurInvIcon dispose:)
				(theControlIcon dispose:)
				(qMarkIcon dispose:)
				(truthIcon dispose:)
				(quizIcon dispose:)
				(ourCursor
					view: 903
					setLoop: 0
					setCel: 5
					setMotion: MoveTo (hari x?) (- (hari y?) 30) self
				)
			)
			(5
				(theMusic2 number: 927 setLoop: 1 play:)
				(messager say: 20 0 21 0 self)
			)
			(6
				(ourCursor dispose:)
				(Cursor showCursor: 1)
				(theIconBar enable:)
				(self dispose:)
			)
		)
	)
)

(instance giveWater of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff: points: 213 1)
				((ScriptID 895 0) setMotion: PolyPath 99 143 self)
			)
			(1
				(Dipper message: 92 cel: 0)
				(messager say: 14 93 7 0 self)
			)
			(2
				((ScriptID 895 0) normalize:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance fillDipper of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff: points: 212 1)
				(Dipper cel: 1 message: 93)
				((ScriptID 895 0) setMotion: PolyPath 103 133 self)
			)
			(1
				((ScriptID 895 0)
					view: 212
					setLoop: 0
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(2
				(messager say: 4 146 0 0 self)
			)
			(3
				((ScriptID 895 0) normalize:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance poorRichard of Actor
	(properties
		noun 14
		view 206
		loop 2
		priority 6
		signal $4810
		detailLevel 3
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(6 (messager say: 14 6 8))
			(7
				(if (> gabbedPR 1)
					(curRoom setScript: sFreePoorRichard)
				else
					(messager say: 22 7 31)
				)
			)
			(85
				(curRoom setScript: sTalkToPoorRichard)
			)
			(92 (messager say: 14 92 5 0 0))
			(93
				(curRoom setScript: giveWater)
			)
			(84
				(theMusic2 number: 927 setLoop: 1 play:)
				(super doVerb: theVerb)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance watchMan of Actor
	(properties
		noun 23
		signal $4800
	)
)

(instance hari of Actor
	(properties
		view 211
		loop 1
		signal $6800
		illegalBits $0000
	)
)

(instance door of Prop
	(properties
		x 139
		y 50
		noun 16
		view 210
		loop 1
		signal $6800
	)
)

(instance bell of Prop
	(properties)
)

(instance hole of View
	(properties
		x 108
		y 177
		view 210
		cel 1
		priority 1
		signal $6810
	)
)

(instance woodThing of View
	(properties
		x 38
		y 94
		view 210
		cel 2
		priority 5
		signal $6811
	)
	
	(method (doVerb theVerb)
		(if (cast contains: poorRichard)
			(poorRichard doVerb: theVerb)
		else
			(theMusic2 number: 927 setLoop: 1 play:)
			(super doVerb: theVerb)
		)
	)
)

(instance dipper of View
	(properties
		x 116
		y 94
		noun 8
		approachX 142
		approachY 132
		view 210
		priority 2
		signal $6810
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(7
				(self hide:)
				(theGame points: 211 1)
				(bucket init:)
				(ego get: 6)
				(curRoom setScript: putDipper 0 1)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance Dipper of TWInvItem
	(properties
		loop 1
		message 92
		signal $0002
		noun 6
		modNum 210
	)
)

(instance poorRichardTalker of BalloonTalker
	(properties
		x 70
		y 107
		talkWidth 160
	)
)

(instance watchManTalker of BalloonTalker
	(properties
		x 130
		y 100
		talkWidth 150
	)
)

(instance hari1Talker of BalloonTalker
	(properties
		x 150
		y 90
		talkWidth 150
	)
	
	(method (dispose)
		(super dispose: &rest)
		(poorRichard startUpd:)
	)
	
	(method (say)
		(poorRichard stopUpd:)
		(super say: &rest)
	)
)

(instance hari2Talker of BalloonTalker
	(properties
		x 35
		y 105
		talkWidth 150
		tailPosn 1
	)
	
	(method (dispose)
		(super dispose: &rest)
		(poorRichard startUpd:)
	)
	
	(method (say)
		(poorRichard stopUpd:)
		(super say: &rest)
	)
)

(instance alleyEnter of ExitFeature
	(properties
		nsTop 31
		nsLeft 206
		nsBottom 96
		nsRight 248
		cursor 911
		exitDir 4
		noun 1
	)
)

(instance barrel of Feature
	(properties
		x 274
		y 87
		noun 2
		nsTop 79
		nsLeft 266
		nsBottom 96
		nsRight 283
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(7 (messager say: 2 7 1))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance alley of Feature
	(properties
		x 228
		y 54
		noun 1
		nsTop 33
		nsLeft 219
		nsBottom 76
		nsRight 238
		sightAngle 40
	)
)

(instance bigBuilding of Feature
	(properties
		x 258
		y 43
		noun 3
		nsTop 6
		nsLeft 242
		nsBottom 81
		nsRight 317
		sightAngle 40
	)
)

(instance bucket of Feature
	(properties
		x 122
		y 103
		noun 4
		nsTop 90
		nsLeft 111
		nsBottom 117
		nsRight 134
		sightAngle 40
		approachX 143
		approachY 132
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(92
				(curRoom setScript: fillDipper)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance crates of Feature
	(properties
		y 65
		noun 5
	)
	
	(method (doVerb theVerb)
		(= global215 48)
		(super doVerb: theVerb)
	)
)

(instance eastRoad of Feature
	(properties
		y 65
		noun 9
	)
)

(instance westRoad of Feature
	(properties
		y 65
		noun 24
	)
)

(instance hills of Feature
	(properties
		y 65
		noun 10
	)
)

(instance idiotSign of Feature
	(properties
		x 62
		y 65
		noun 11
		nsTop 58
		nsLeft 43
		nsBottom 73
		nsRight 81
		sightAngle 40
	)
)

(instance stocks of Feature
	(properties
		x 65
		y 114
		noun 22
		nsTop 93
		nsLeft 26
		nsBottom 136
		nsRight 105
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(7
				(poorRichard doVerb: theVerb)
			)
			(84
				(theMusic2 number: 927 setLoop: 1 play:)
				(super doVerb: theVerb)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance shedDoor of Feature
	(properties
		x 153
		y 93
		noun 21
		nsTop 71
		nsLeft 142
		nsBottom 115
		nsRight 164
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 84)
			(theMusic2 number: 927 setLoop: 1 play:)
			(super doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance shedLock of Feature
	(properties
		x 164
		y 91
		nsTop 87
		nsLeft 158
		nsBottom 95
		nsRight 170
		sightAngle 40
	)
)

(instance shed of Feature
	(properties
		noun 17
	)
)

(instance shedWindow of Feature
	(properties
		x 190
		y 83
		noun 18
		nsTop 67
		nsLeft 174
		nsBottom 99
		nsRight 207
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(= global215 8)
		(super doVerb: theVerb)
	)
)

(instance smallBuild of Feature
	(properties
		x 12
		y 81
		noun 19
		nsTop 58
		nsBottom 105
		nsRight 24
		sightAngle 40
	)
)

(instance eastEFeature of ExitFeature
	(properties
		nsTop 74
		nsLeft 311
		nsBottom 169
		nsRight 319
		cursor 913
		exitDir 2
		noun 9
	)
)

(instance westEFeature of ExitFeature
	(properties
		nsTop 119
		nsBottom 189
		nsRight 10
		cursor 911
		exitDir 4
		noun 24
	)
)

(instance ourCursor of Actor
	(properties
		x 120
		y 120
		view 999
		cycleSpeed 0
		moveSpeed 0
	)
	
	(method (init)
		(self setPri: 15)
		(super init: &rest)
	)
)

(instance theWalkIcon of View
	(properties
		y -5
		view 900
		priority 14
		signal $5010
	)
)

(instance theLookIcon of View
	(properties
		x 39
		y -5
		view 900
		loop 1
		priority 14
		signal $5010
	)
)

(instance theHandIcon of View
	(properties
		x 69
		y -5
		view 900
		loop 2
		priority 14
		signal $5010
	)
)

(instance theTalkIcon of View
	(properties
		x 99
		y -5
		view 900
		loop 3
		priority 14
		signal $5010
	)
)

(instance theInvIcon of View
	(properties
		x 129
		y -5
		view 900
		loop 4
		priority 14
		signal $5010
	)
)

(instance theCurInvIcon of View
	(properties
		x 159
		y -5
		view 900
		loop 5
		priority 14
		signal $5010
	)
)

(instance theControlIcon of View
	(properties
		x 194
		y -5
		view 900
		loop 6
		priority 14
		signal $5010
	)
)

(instance qMarkIcon of View
	(properties
		x 224
		y -5
		view 900
		loop 7
		priority 14
		signal $5010
	)
)

(instance truthIcon of View
	(properties
		x 254
		y -5
		view 900
		loop 8
		priority 14
		signal $5010
	)
)

(instance quizIcon of View
	(properties
		x 284
		y -5
		view 900
		loop 9
		priority 14
		signal $5010
	)
)
