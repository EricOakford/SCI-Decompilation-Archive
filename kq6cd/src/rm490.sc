;;; Sierra Script 1.0 - (do not remove this comment)
(script# 490)
(include sci.sh)
(use Main)
(use Kq6Sound)
(use NewRoomCue)
(use Kq6Talker)
(use Kq6Procs)
(use Conv)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use Timer)
(use Motion)
(use Actor)
(use System)

(public
	rm490 0
	White_Queen 52
	Red_Queen 53
)

(local
	local0
	[local1 2]
	local3
	[local4 30]
	[local34 7] = [222 211 205 197 191 185 179]
	[local41 9] = [89 93 99 109 116 124 131]
	local50
	local51
	local52
	local53 =  3
)
(instance rm490 of KQ6Room
	(properties
		noun 3
		picture 490
		walkOffEdge 1
		autoLoad 0
	)
	
	(method (init)
		(self
			addObstacle:
				((Polygon new:)
					type: 2
					init: 71 124 8 189 0 189 0 0 319 0 319 189 300 189 243 124
					yourself:
				)
		)
		(super init: &rest)
		(theMusic number: 490 setLoop: -1 play:)
		(features
			add: theSky thePath theSteps
			eachElementDo: #init
		)
		(redKnight addToPic:)
		(whiteKnight addToPic:)
		(if
			(and
				(Btst 39)
				(== ((inventory at: 41) owner?) curRoomNum)
			)
			(redScarf init:)
		)
		(curRoom setScript: egoEnters)
	)
	
	(method (doit)
		(cond 
			((curRoom script?))
			((== (ego edgeHit?) 3) (curRoom walkOffEdge: 1 setScript: walkOut))
			((<= (ego y?) 128)
				(if (and (not local3) local52)
					(ego setMotion: 0)
					(curRoom cue:)
				else
					(ego posn: (ego x?) (+ (ego y?) 1) setMotion: 0)
					(curRoom setScript: knightBlock 0 3)
				)
			)
		)
		(super doit:)
	)
	
	(method (cue)
		(switch (++ local3)
			(1
				(theGame handsOff:)
				(messager say: 1 0 10 0 self)
			)
			(2
				(curRoom setScript: queensLeave 0 0)
			)
		)
	)
	
	(method (newRoom n)
		(roomTimer dispose: delete:)
		(super newRoom: n)
	)
	
	(method (scriptCheck &tmp temp0)
		(= temp0 0)
		(if local52
			(messager say: 0 0 234 0 0 899)
		else
			(= temp0 1)
		)
		(return temp0)
	)
)

(instance myConv of Conversation
	(properties)
)

(instance whiteKnight of Actor
	(properties
		x 89
		y 124
		noun 8
		view 492
		signal $4800
		illegalBits $0000
		xStep 8
	)
	
	(method (doVerb theVerb)
		(if (cast contains: whiteQueen)
			(messager say: 7 0 11 1)
		else
			(= local0 8)
			(switch theVerb
				(5
					(curRoom setScript: knightBlock 0 theVerb)
				)
				(2
					(curRoom setScript: knightBlock 0 theVerb)
				)
				(46
					(curRoom setScript: knightBlock 0 theVerb)
				)
				(72
					(curRoom setScript: knightBlock 0 theVerb)
				)
				(1
					(super doVerb: theVerb &rest)
				)
				(else 
					(curRoom setScript: knightBlock 0 0)
				)
			)
		)
	)
)

(instance redKnight of Actor
	(properties
		x 222
		y 124
		noun 7
		view 493
		cel 1
		signal $4800
		illegalBits $0000
		xStep 8
	)
	
	(method (doVerb theVerb)
		(if (cast contains: redQueen)
			(messager say: 7 0 11 1)
		else
			(= local0 7)
			(switch theVerb
				(5
					(curRoom setScript: knightBlock 0 theVerb)
				)
				(2
					(curRoom setScript: knightBlock 0 theVerb)
				)
				(46
					(curRoom setScript: knightBlock 0 theVerb)
				)
				(72
					(curRoom setScript: knightBlock 0 theVerb)
				)
				(1
					(if (and (not (-- local53)) (== prevRoomNum 99))
						(ego get: 6)
					)
					(super doVerb: theVerb &rest)
				)
				(else 
					(curRoom setScript: knightBlock 0 0)
				)
			)
		)
	)
)

(instance redQueen of Actor
	(properties
		noun 5
		view 494
		priority 6
		signal $4810
		cycleSpeed 9
		illegalBits $0000
	)
	
	(method (doit)
		(cond 
			(
				(and
					(not (soundFx2 handle?))
					(== cel 2)
					(== loop 3)
					(== view 494)
				)
				(soundFx2 number: 493 setLoop: 1 play:)
			)
			(
				(and
					(not (soundFx2 handle?))
					(== cel 2)
					(== loop 1)
					(== view 495)
				)
				(soundFx2 number: 493 setLoop: 1 play:)
			)
		)
		(super doit: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(46 (messager say: 5 46 0 1))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance whiteQueen of Actor
	(properties
		noun 6
		view 496
		priority 6
		signal $4810
		cycleSpeed 9
		illegalBits $0000
	)
	
	(method (doit)
		(super doit: &rest)
		(cond 
			(
				(and
					(not (soundFx2 handle?))
					(== cel 2)
					(== loop 3)
					(== view 496)
				)
				(soundFx2 number: 493 setLoop: 1 play:)
			)
			(
				(and
					(not (soundFx2 handle?))
					(== cel 2)
					(== loop 1)
					(== view 497)
				)
				(soundFx2 number: 493 setLoop: 1 play:)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(46
				(curRoom setScript: coalToQueen)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance redScarf of Prop
	(properties
		x 177
		y 134
		noun 4
		view 494
		loop 4
		signal $6000
		cycleSpeed 10
	)
	
	(method (init)
		(if (cast contains: redQueen)
			(self setCycle: End self)
		else
			(self view: 4900 setLoop: 0 cel: 3 stopUpd:)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(curRoom setScript: getScarf)
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (cue)
		(self view: 4900 setLoop: 0 cel: 3 stopUpd:)
	)
)

(instance thePath of Feature
	(properties
		noun 9
		onMeCheck $0002
	)
)

(instance theSteps of Feature
	(properties
		noun 11
		onMeCheck $0004
	)
)

(instance theSky of Feature
	(properties
		noun 10
	)
	
	(method (init)
		(self setOnMeCheck: 1 16)
		(super init: &rest)
	)
)

(instance knightBlock of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(cond 
					((or (== register 3) (== register 5)) (ego setMotion: PolyPath 150 140 self))
					((== local0 8) (ego setMotion: PolyPath 130 145 self))
					(else (ego setMotion: PolyPath 178 145 self))
				)
				(self setScript: knightBoundForward self)
			)
			(1 (ego setHeading: 0 self))
			(2)
			(3
				(redKnight view: 493 setLoop: 0 cel: 2)
				(whiteKnight view: 492 setLoop: 0 cel: 2)
				(= ticks 30)
			)
			(4
				(switch register
					(3 (messager say: 3 3 5 0 self))
					(5 (messager say: 3 3 5 0 self))
					(2
						(if (and (Btst 39) (Btst 69))
							(messager say: 7 2 9 0 self)
						else
							(Bset 69)
							(messager say: 7 2 7 0 self)
						)
					)
					(46
						(messager say: 7 46 0 0 self)
					)
					(72
						(messager say: local0 72 0 0 self)
					)
					(0 (messager say: 7 0 0 0 self))
					(else 
						(theGame handsOn:)
						(self dispose:)
					)
				)
			)
			(5
				(hoofSound number: 492 setLoop: 1 play:)
				(client setScript: knightBoundBack)
			)
		)
	)
)

(instance queensEnter of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic pause: 1)
				(curRoom drawPic: 490 (if global169 15 else 100))
				(theGlobalSound
					number: 491
					setLoop: -1
					play: 10
					setVol: 10
				)
				(whiteKnight
					signal: 18432
					view: 4900
					cel: 0
					setLoop: 0
					addToPic:
				)
				(redKnight
					signal: 18432
					view: 4900
					setLoop: 0
					cel: 1
					addToPic:
				)
				(UnLoad 128 492)
				(UnLoad 128 493)
				(= cycles 2)
			)
			(1 (ego setHeading: 0 self))
			(2
				(theGlobalSound fade: 127 10 5 0)
				(ego normal: 0 view: 4900 cel: 2 setCycle: 0 setLoop: 0)
				(= cycles 2)
			)
			(3
				(self setScript: miniQueenPolka self)
			)
			(4 (= seconds 3))
			(5
				(self setScript: queensJumpUp self)
			)
			(6
				(redQueen stopUpd:)
				(whiteQueen stopUpd:)
				(= cycles 2)
			)
			(7
				(curRoom drawPic: 490 (if global169 15 else 100))
				(whiteKnight
					signal: 18432
					view: 4901
					setLoop: 0
					cel: 0
					init:
					setCycle: End self
				)
				(redKnight
					signal: 18432
					view: 4901
					setLoop: 1
					cel: 0
					init:
					setCycle: End self
				)
			)
			(8)
			(9
				(whiteKnight view: 4900 setLoop: 2 cel: 0 addToPic:)
				(redKnight view: 4900 setLoop: 2 cel: 1 addToPic:)
				(UnLoad 128 492)
				(UnLoad 128 493)
				(= cycles 2)
			)
			(10
				(redQueen view: 495 setLoop: 1 cel: 0 setCycle: End self)
				(whiteQueen
					view: 497
					setLoop: 1
					cel: 0
					setCycle: End self
				)
			)
			(11)
			(12
				(redQueen view: 494 setLoop: 0 cel: 1)
				(whiteQueen view: 496 setLoop: 0 cel: 0)
				(= ticks 12)
			)
			(13
				(curRoom drawPic: 490 (if global169 15 else 100))
				(whiteKnight
					signal: 18432
					view: 4901
					setLoop: 0
					cel: 3
					init:
					setCycle: Beg self
				)
				(redKnight
					signal: 18432
					view: 4901
					setLoop: 1
					cel: 3
					init:
					setCycle: Beg self
				)
				(UnLoad 128 492)
				(UnLoad 128 493)
			)
			(14)
			(15
				(whiteKnight view: 4900 setLoop: 1 cel: 0 addToPic:)
				(redKnight view: 4900 setLoop: 1 cel: 1 addToPic:)
				(UnLoad 128 492)
				(UnLoad 128 493)
				(= cycles 2)
			)
			(16
				(if (and (Btst 39) (ego has: 6))
					(curRoom setScript: coalQueenTalk)
				else
					(curRoom setScript: firstQueenTalk)
				)
			)
		)
	)
)

(instance firstQueenTalk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset 59)
				(myConv
					add: -1 1 0 2 1
					add: -1 1 0 2 2
					add: -1 1 0 2 3
					add: -1 1 0 2 4
					add: -1 1 0 2 5
					add: -1 1 0 2 6
					init: self
				)
			)
			(1 (= seconds 2))
			(2
				(Bclr 59)
				(redQueen view: 495 setLoop: 2 cel: 0 setCycle: End self)
			)
			(3
				(redQueen view: 495 setLoop: 1 cel: 3 setCycle: Beg self)
				(whiteQueen
					view: 497
					setLoop: 1
					cel: 3
					setCycle: Beg self
				)
			)
			(4)
			(5
				(redQueen view: 494 setLoop: 0 cel: 2)
				(whiteQueen view: 496 setLoop: 0 cel: 2)
				(= ticks 30)
			)
			(6
				(myConv add: -1 1 0 2 7 add: -1 1 0 2 8 init: self)
			)
			(7
				(Bset 59)
				(redQueen view: 495 setLoop: 1 cel: 0 setCycle: End self)
				(if (not (soundFx2 handle?))
					(soundFx2 number: 493 setLoop: 1 play:)
				)
			)
			(8 (messager say: 1 0 2 9 self))
			(9
				(redQueen cel: 3 setCycle: Beg self)
			)
			(10 (= ticks 30))
			(11
				(redQueen view: 494 setLoop: 0 cel: 2)
				(= ticks 30)
			)
			(12
				(ego
					view: 491
					normal: 0
					setLoop: 2
					cel: 0
					cycleSpeed: 0
					setCycle: CT 4 1 self
				)
			)
			(13
				(Bclr 59)
				(messager say: 1 0 2 10 self)
			)
			(14
				(ego view: 4900 setLoop: 0 cel: 2)
				(= cycles 3)
			)
			(15
				(myConv add: -1 1 0 2 11 add: -1 1 0 2 12 init: self)
			)
			(16
				(redQueen view: 495 setLoop: 1 cel: 0 setCycle: End self)
				(whiteQueen
					view: 497
					setLoop: 1
					cel: 0
					setCycle: End self
				)
			)
			(17)
			(18
				(Bset 59)
				(redQueen view: 494 setLoop: 0 cel: 1)
				(whiteQueen view: 496 setLoop: 0 cel: 0)
				(= ticks 30)
			)
			(19
				(myConv add: -1 1 0 2 13 add: -1 1 0 2 14 init: self)
			)
			(20
				(Bset 39)
				(= local50 1)
				(curRoom setScript: queensLeave 0 1)
			)
		)
	)
)

(instance coalQueenTalk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset 59)
				(if (Btst 89)
					(myConv
						add: -1 1 0 4 1
						add: -1 1 0 4 2
						add: -1 1 0 4 3
						add: -1 1 0 4 4
						add: -1 1 0 4 5
						add: -1 1 0 4 6
						init: self
					)
				else
					(myConv
						add: -1 1 0 3 1
						add: -1 1 0 3 2
						add: -1 1 0 3 3
						add: -1 1 0 3 4
						add: -1 1 0 3 5
						add: -1 1 0 3 6
						add: -1 1 0 3 7
						init: self
					)
				)
			)
			(1
				(redQueen view: 4951 setLoop: 1 cel: 3 setCycle: Beg self)
				(whiteQueen
					view: 497
					setLoop: 1
					cel: 3
					setCycle: Beg self
				)
				(UnLoad 128 494)
				(UnLoad 128 496)
			)
			(2)
			(3
				(redQueen view: 4951 setLoop: 0 cel: 0)
				(whiteQueen view: 496 setLoop: 0 cel: 2)
				(UnLoad 128 497)
				(= cycles 4)
			)
			(4
				(Bclr 59)
				(if (Btst 89)
					(messager say: 1 0 4 7 self)
				else
					(Bset 89)
					(messager say: 1 0 3 8 self)
				)
			)
			(5
				(theGame handsOn:)
				(ego reset: 3)
				(= local3 0)
				(= local52 1)
				(roomTimer setReal: curRoom 15)
				(self dispose:)
			)
		)
	)
)

(instance getScarf of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego illegalBits: 0 setMotion: PolyPath 163 136 self)
			)
			(1 (ego loop: 6) (= ticks 12))
			(2 (messager say: 4 5 0 1 self))
			(3
				(ego
					view: 491
					normal: 0
					loop: 4
					cel: 0
					posn: 172 140
					cycleSpeed: 10
					setCycle: CT 3 1 self
				)
			)
			(4
				(theGame givePoints: 1)
				(redScarf dispose:)
				(ego setCycle: End self)
			)
			(5
				(ego
					reset: 6
					get: 41
					posn: 163 136
					setMotion: PolyPath 150 170 self
				)
			)
			(6
				(theGame handsOn:)
				(ego setHeading: 180)
				(self dispose:)
			)
		)
	)
)

(instance egoEnters of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					posn: 160 255
					setScale: Scaler 100 99 190 0
					init:
					reset:
					setMotion: MoveTo 144 183 self
				)
			)
			(1
				(if (and (Btst 39) (ego has: 6))
					(= cycles 2)
				else
					(theGame handsOn:)
					(self dispose:)
				)
			)
			(2
				(if (== (theMusic prevSignal?) 10)
					(theMusic prevSignal: 0)
					(theIconBar disable: 6)
					(curRoom setScript: queensEnter)
					(self dispose:)
				else
					(-- state)
					(= cycles 1)
				)
			)
		)
	)
)

(instance walkOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theMusic fade:)
				(theGlobalSound fade:)
				(ego setMotion: MoveTo (ego x?) 255 self)
			)
			(1 (curRoom newRoom: 480))
		)
	)
)

(instance queensJumpUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (ego has: 6)
					(redQueen
						view: 4952
						setLoop: 0
						cel: 0
						setPri: 7
						posn: 177 148
						setCycle: End
					)
				else
					(redQueen
						view: 494
						setLoop: 1
						cel: 0
						setPri: 7
						posn: 177 148
						setCycle: End
					)
				)
				(whiteQueen
					view: 496
					setLoop: 1
					cel: 0
					setPri: 7
					posn: 143 148
					setCycle: End self
				)
			)
			(1
				(redQueen posn: 177 138 cel: 0 setCycle: End self)
				(whiteQueen posn: 143 138 cel: 0 setCycle: End self)
			)
			(2)
			(3
				(redQueen posn: 177 116 cel: 0 setCycle: End self)
				(whiteQueen posn: 143 116 cel: 0 setCycle: End self)
			)
			(4)
			(5
				(redQueen
					posn: 177 116
					cel: 0
					setPri: 9
					setCycle: End self
				)
				(whiteQueen
					posn: 143 116
					cel: 0
					setPri: 9
					setCycle: End self
				)
			)
			(6)
			(7
				(if (ego has: 6)
					(redQueen view: 4951 setLoop: 0 cel: 0 posn: 177 124)
				else
					(redQueen view: 494 setLoop: 0 cel: 2 posn: 177 124)
				)
				(whiteQueen posn: 143 124 setLoop: 0 cel: 2)
				(= seconds 2)
			)
			(8 (self dispose:))
		)
	)
)

(instance miniQueenSplit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(whiteQueen setLoop: 4 setPri: 7 posn: 186 112 show:)
				(redQueen setLoop: 5 setPri: 7 posn: 197 110 show:)
				(= ticks 8)
			)
			(1
				(whiteQueen posn: 186 113)
				(redQueen posn: 197 111)
				(= ticks 8)
			)
			(2
				(whiteQueen posn: 185 108)
				(redQueen posn: 196 107)
				(= ticks 8)
			)
			(3
				(whiteQueen posn: 184 110)
				(redQueen posn: 194 108)
				(= ticks 8)
			)
			(4
				(whiteQueen posn: 183 105)
				(redQueen posn: 193 105)
				(= ticks 8)
			)
			(5
				(whiteQueen posn: 182 106)
				(redQueen posn: 191 106)
				(= ticks 8)
			)
			(6
				(whiteQueen posn: 181 102)
				(redQueen posn: 189 102)
				(= ticks 8)
			)
			(7
				(whiteQueen posn: 179 103)
				(redQueen posn: 186 103)
				(= ticks 8)
			)
			(8
				(whiteQueen posn: 178 99)
				(redQueen posn: 184 99)
				(= ticks 8)
			)
			(9
				(whiteQueen posn: 176 100)
				(redQueen posn: 182 100)
				(= ticks 8)
			)
			(10
				(whiteQueen posn: 174 96)
				(redQueen posn: 178 96)
				(= ticks 8)
			)
			(11
				(whiteQueen posn: 172 97)
				(redQueen posn: 176 97)
				(= ticks 8)
			)
			(12
				(whiteQueen posn: 168 93)
				(redQueen posn: 172 93)
				(= ticks 8)
			)
			(13
				(whiteQueen posn: 168 94)
				(redQueen posn: 170 94)
				(= ticks 8)
			)
			(14
				(whiteQueen posn: 164 91)
				(redQueen posn: 166 91)
				(= ticks 8)
			)
			(15
				(whiteQueen posn: 161 91)
				(redQueen posn: 163 91)
				(= ticks 8)
			)
			(16
				(whiteQueen posn: 158 88)
				(redQueen posn: 160 88)
				(= ticks 8)
			)
			(17
				(whiteQueen setPri: 5 posn: 154 93)
				(redQueen setPri: 5 posn: 156 93)
				(= ticks 8)
			)
			(18
				(whiteQueen posn: 154 92)
				(redQueen posn: 156 92)
				(= ticks 8)
			)
			(19
				(whiteQueen posn: 154 95)
				(redQueen posn: 156 95)
				(= ticks 8)
			)
			(20
				(whiteQueen dispose:)
				(redQueen dispose:)
				(theIconBar enable: 6)
				(self dispose:)
			)
		)
	)
)

(instance hopOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(redQueen
					view: 494
					setLoop: 3
					cel: 0
					posn: 176 124
					cycleSpeed: 10
					setCycle: End
				)
				(whiteQueen
					view: 496
					setLoop: 3
					cel: 0
					posn: 143 124
					cycleSpeed: 10
					setCycle: End self
				)
			)
			(1
				(if local50 (= local50 0) (redScarf init:))
				(redQueen setLoop: 2 cel: 0 posn: 176 124)
				(whiteQueen setLoop: 2 cel: 0 posn: 143 124)
				(= ticks 8)
			)
			(2
				(redQueen cel: 1 posn: 176 124)
				(whiteQueen cel: 1 posn: 143 124)
				(= ticks 8)
			)
			(3
				(redQueen cel: 2 posn: 176 122)
				(whiteQueen cel: 2 posn: 143 122)
				(= ticks 8)
			)
			(4
				(redQueen cel: 3 posn: 176 120)
				(whiteQueen cel: 3 posn: 143 120)
				(= ticks 8)
			)
			(5
				(redQueen cel: 4 posn: 176 119)
				(whiteQueen cel: 4 posn: 143 119)
				(= ticks 8)
			)
			(6
				(redQueen cel: 5 posn: 176 118)
				(whiteQueen cel: 5 posn: 143 118)
				(= ticks 8)
			)
			(7
				(redQueen cel: 0)
				(whiteQueen cel: 0)
				(= ticks 8)
			)
			(8
				(redQueen cel: 1)
				(whiteQueen cel: 1)
				(= ticks 8)
			)
			(9
				(redQueen cel: 2 posn: 176 116)
				(whiteQueen cel: 2 posn: 143 116)
				(= ticks 8)
			)
			(10
				(redQueen cel: 3 posn: 176 114 setPri: 7)
				(whiteQueen cel: 3 posn: 143 114 setPri: 7)
				(= ticks 8)
			)
			(11
				(redQueen cel: 4 posn: 176 118)
				(whiteQueen cel: 4 posn: 143 118)
				(= ticks 8)
			)
			(12
				(redQueen cel: 5 posn: 176 120)
				(whiteQueen cel: 5 posn: 143 120)
				(= ticks 8)
			)
			(13
				(redQueen cel: 0 posn: 176 124)
				(whiteQueen cel: 0 posn: 143 124)
				(= ticks 8)
			)
			(14
				(redQueen cel: 1 posn: 176 125)
				(whiteQueen cel: 1 posn: 143 125)
				(= ticks 8)
			)
			(15
				(redQueen cel: 2 posn: 176 130)
				(whiteQueen cel: 2 posn: 143 130)
				(= ticks 8)
			)
			(16
				(redQueen cel: 3 posn: 176 134)
				(whiteQueen cel: 3 posn: 143 134)
				(= ticks 8)
			)
			(17
				(redQueen cel: 4 posn: 176 138)
				(whiteQueen cel: 4 posn: 143 138)
				(= ticks 8)
			)
			(18
				(redQueen cel: 5 posn: 176 130)
				(whiteQueen cel: 5 posn: 143 130)
				(= ticks 8)
			)
			(19
				(redQueen cel: 0 posn: 176 145)
				(whiteQueen cel: 0 posn: 143 145)
				(= ticks 8)
			)
			(20
				(redQueen cel: 1 posn: 176 148)
				(whiteQueen cel: 1 posn: 143 148)
				(= ticks 8)
			)
			(21
				(redQueen cel: 2 posn: 176 148)
				(whiteQueen cel: 2 posn: 143 148)
				(= ticks 8)
			)
			(22
				(redQueen cel: 3 posn: 176 152)
				(whiteQueen cel: 3 posn: 143 152)
				(= ticks 8)
			)
			(23
				(redQueen cel: 4 posn: 176 157)
				(whiteQueen cel: 4 posn: 143 157)
				(= ticks 8)
			)
			(24
				(redQueen cel: 0 posn: 176 150)
				(whiteQueen cel: 0 posn: 143 150)
				(= ticks 8)
			)
			(25
				(redQueen hide:)
				(whiteQueen hide:)
				(= ticks 8)
			)
			(26 (self dispose:))
		)
	)
)

(instance knightBoundForward of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= register 0)
				(curRoom drawPic: 490 (if global169 15 else 100))
				(redKnight
					signal: 18432
					view: 493
					cel: 0
					setLoop: 2
					init:
					setCycle: End self
				)
				(whiteKnight
					signal: 18432
					view: 492
					cel: 0
					setLoop: 2
					init:
					setCycle: End self
				)
			)
			(1)
			(2 (= seconds 2))
			(3
				(hoofSound number: 492 setLoop: 1 play:)
				(redKnight view: 493 cel: 0 setLoop: 1)
				(whiteKnight view: 492 cel: 0 setLoop: 1)
				(= cycles 3)
			)
			(4
				(redKnight cel: register posn: [local34 register] 124)
				(whiteKnight cel: register posn: [local41 register] 124)
				(if (<= (++ register) 6) (-- state))
				(= ticks 10)
			)
			(5
				(redKnight setLoop: 0 cel: 2 stopUpd:)
				(whiteKnight setLoop: 0 cel: 2 stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance knightBoundBack of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= register 6)
				(curRoom drawPic: 490 (if global169 15 else 100))
				(redKnight signal: 18432 view: 493 setLoop: 1 init:)
				(whiteKnight signal: 18432 view: 492 setLoop: 1 init:)
				(= cycles 2)
			)
			(1
				(redKnight cel: register posn: [local34 register] 124)
				(whiteKnight cel: register posn: [local41 register] 124)
				(if (>= (-- register) 0) (-- state))
				(= ticks 10)
			)
			(2
				(ego setMotion: MoveTo (ego x?) 160 self)
			)
			(3
				(whiteKnight view: 4900 setLoop: 0 cel: 0 addToPic:)
				(redKnight view: 4900 setLoop: 0 cel: 1 addToPic:)
				(UnLoad 128 492)
				(UnLoad 128 493)
				(= cycles 2)
			)
			(4
				(if (not (Btst 39))
					(theIconBar disable: 6)
					(curRoom setScript: queensEnter)
				else
					(theGame handsOn:)
				)
				(self dispose:)
			)
		)
	)
)

(instance roomTimer of Timer
	(properties)
)

(instance miniQueenPolka of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(whiteQueen
					view: 496
					setLoop: 4
					setPri: 5
					posn: 154 93
					init:
				)
				(redQueen
					view: 494
					setLoop: 5
					setPri: 5
					posn: 156 93
					init:
				)
				(= ticks 8)
			)
			(1
				(whiteQueen posn: 158 88)
				(redQueen posn: 160 88)
				(= ticks 8)
			)
			(2
				(whiteQueen setPri: 7 posn: 161 91)
				(redQueen setPri: 7 posn: 163 91)
				(= ticks 8)
			)
			(3
				(whiteQueen posn: 164 91)
				(redQueen posn: 166 91)
				(= ticks 8)
			)
			(4
				(whiteQueen posn: 168 94)
				(redQueen posn: 170 94)
				(= ticks 8)
			)
			(5
				(whiteQueen posn: 168 93)
				(redQueen posn: 172 93)
				(= ticks 8)
			)
			(6
				(whiteQueen posn: 172 97)
				(redQueen posn: 176 97)
				(= ticks 8)
			)
			(7
				(whiteQueen posn: 174 96)
				(redQueen posn: 178 96)
				(= ticks 8)
			)
			(8
				(whiteQueen posn: 176 100)
				(redQueen posn: 182 100)
				(= ticks 8)
			)
			(9
				(whiteQueen posn: 178 99)
				(redQueen posn: 184 99)
				(= ticks 8)
			)
			(10
				(whiteQueen posn: 179 103)
				(redQueen posn: 186 103)
				(= ticks 8)
			)
			(11
				(whiteQueen posn: 181 102)
				(redQueen posn: 189 102)
				(= ticks 8)
			)
			(12
				(whiteQueen posn: 182 106)
				(redQueen posn: 191 106)
				(= ticks 8)
			)
			(13
				(whiteQueen posn: 183 105)
				(redQueen posn: 193 105)
				(= ticks 8)
			)
			(14
				(whiteQueen posn: 184 110)
				(redQueen posn: 194 108)
				(= ticks 8)
			)
			(15
				(whiteQueen posn: 185 108)
				(redQueen posn: 196 107)
				(= ticks 8)
			)
			(16
				(whiteQueen posn: 186 113)
				(redQueen posn: 197 111)
				(= ticks 8)
			)
			(17
				(whiteQueen posn: 186 112)
				(redQueen posn: 197 110)
				(= ticks 8)
			)
			(18
				(whiteQueen posn: 186 118)
				(redQueen posn: 197 118)
				(= ticks 8)
			)
			(19 (self dispose:))
		)
	)
)

(instance Red_Queen of Kq6Talker
	(properties
		disposeWhenDone 1
		talkWidth 213
		name "Red Queen"
	)
	
	(method (init)
		(if (Btst 59)
			(cond 
				(
					(and
						(== (redQueen loop?) 0)
						(== (redQueen view?) 494)
					)
					(self
						view: 495
						loop: 4
						x: 173
						y: 82
						textX: -89
						textY: -72
					)
					(super init: 0 0 tRMouth2 &rest)
				)
				(
					(and
						(== (redQueen cel?) 3)
						(== (redQueen loop?) 1)
						(== (redQueen view?) 495)
					)
					(self
						view: 495
						loop: 4
						x: 171
						y: 82
						textX: -89
						textY: -72
					)
					(super init: 0 0 tRMouth2 &rest)
				)
				(else
					(self
						view: 890
						loop: 0
						cel: 1
						x: 309
						y: 5
						textX: -223
						textY: 6
					)
					(super init: 0 0 0 &rest)
				)
			)
		else
			(self
				view: 495
				loop: 3
				x: 174
				y: 80
				textX: -89
				textY: -72
			)
			(super init: 0 0 tRMouth &rest)
		)
	)
)

(instance tRMouth of Prop
	(properties
		view 495
		loop 3
	)
)

(instance tRMouth2 of Prop
	(properties
		view 495
		loop 4
	)
)

(instance White_Queen of Kq6Talker
	(properties
		x 142
		y 82
		view 890
		cel 1
		disposeWhenDone 1
		talkWidth 213
		textX -127
		textY -79
		name "White Queen"
	)
	
	(method (init)
		(if (Btst 59)
			(if
				(and
					(not (whiteQueen cel?))
					(not (whiteQueen loop?))
					(== (whiteQueen view?) 496)
				)
				(self
					view: 497
					setLoop: 4
					x: 146
					y: 82
					textX: -127
					textY: -73
				)
				(super init: 0 0 tWMouth2 &rest)
			else
				(self
					view: 890
					setLoop: 0
					cel: 1
					x: 5
					y: 5
					textX: 10
					textY: 6
				)
				(super init: 0 0 0 &rest)
			)
		else
			(self
				view: 497
				loop: 3
				x: 135
				y: 76
				textX: -127
				textY: -63
			)
			(super init: 0 0 tWMouth &rest)
		)
	)
)

(instance tWMouth of Prop
	(properties
		view 497
		loop 3
	)
)

(instance tWMouth2 of Prop
	(properties
		view 497
		loop 4
	)
)

(instance hoofSound of Kq6Sound
	(properties)
)

(instance coalToQueen of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= local3 99)
				(ego put: 6 490)
				(ego illegalBits: 0 setMotion: MoveTo 176 136 self)
			)
			(1 (ego setHeading: 335 self))
			(2
				(ego view: 491 loop: 3 cel: 0 normal: 0 cycleSpeed: 10)
				(= cycles 4)
			)
			(3
				(ego setCycle: CT 4 1 self)
				(whiteQueen view: 497 setLoop: 0 cel: 0 setCycle: CT 3 1)
			)
			(4
				(theGame givePoints: 1)
				(messager say: 6 46 0 1 self)
			)
			(5 (ego setCycle: End self))
			(6
				(ego reset: 7)
				(whiteQueen setCycle: Beg self)
			)
			(7
				(messager say: 6 46 0 2 self)
			)
			(8
				(whiteQueen
					view: 497
					setLoop: 1
					cel: 0
					setCycle: End self
				)
			)
			(9
				(Bset 59)
				(whiteQueen view: 496 setLoop: 0 cel: 0)
				(= cycles 4)
			)
			(10
				(messager say: 6 46 0 3 self)
			)
			(11
				(redQueen view: 4951 setLoop: 1 cel: 0 setCycle: End self)
				(if (not (soundFx2 handle?))
					(soundFx2 number: 493 setLoop: 1 play:)
				)
			)
			(12
				(redQueen view: 494 setLoop: 0 cel: 1)
				(= cycles 4)
			)
			(13
				(messager say: 6 46 0 4 self)
			)
			(14
				(redQueen view: 4951 setLoop: 1 cel: 3 setCycle: Beg self)
				(whiteQueen
					view: 497
					setLoop: 1
					cel: 3
					setCycle: Beg self
				)
			)
			(15)
			(16
				(Bclr 59)
				(redQueen view: 4951 setLoop: 0 cel: 0)
				(whiteQueen view: 497 setLoop: 0 cel: 0)
				(= cycles 4)
			)
			(17
				(messager say: 6 46 0 5 self)
			)
			(18
				(ego
					view: 491
					loop: 3
					cel: 0
					normal: 0
					cycleSpeed: 10
					setCycle: CT 4 1 self
				)
				(whiteQueen
					view: 497
					setLoop: 0
					cel: 0
					setCycle: CT 3 1 self
				)
			)
			(19)
			(20
				(whiteQueen setCycle: Beg self)
				(ego setCycle: End self)
			)
			(21)
			(22
				(ego reset: 7 get: 10)
				(= cycles 6)
			)
			(23
				(messager say: 6 46 0 6 self)
			)
			(24
				(Bset 59)
				(curRoom setScript: queensLeave 0 46)
			)
		)
	)
)

(instance queensLeave of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks 8))
			(1
				(self setScript: hopOut self)
			)
			(2
				(curRoom drawPic: 490 (if global169 15 else 100))
				(redKnight
					signal: 18432
					view: 4900
					setLoop: 0
					cel: 1
					init:
				)
				(whiteKnight
					signal: 18432
					view: 4900
					setLoop: 0
					cel: 0
					init:
				)
				(= ticks 12)
			)
			(3
				(switch register
					(1
						(messager say: 1 0 2 15 self)
					)
					(46
						(messager say: 6 46 0 7 self)
					)
					(else  (self cue:))
				)
			)
			(4 (= seconds 3))
			(5
				(theGlobalSound fade: 40 10 2 0)
				(whiteQueen setScript: miniQueenSplit)
				(= ticks 60)
			)
			(6
				(switch register
					(1
						(messager say: 1 0 2 16 self)
					)
					(46
						(myConv
							add: -1 6 46 0 8
							add: -1 6 46 0 9
							add: -1 6 46 0 10
							add: -1 6 46 0 11
							init: self
						)
					)
					(else  (self cue:))
				)
			)
			(7
				(if (== register 46)
					(= register (ego cycleSpeed?))
					(ego
						view: 499
						cel: 0
						normal: 0
						cycleSpeed: 8
						setCycle: End self
					)
					(= local51 1)
				else
					(= cycles 2)
				)
			)
			(8 (= seconds 2))
			(9
				(if local51
					(ego cycleSpeed: register setCycle: Beg self)
				else
					(= cycles 2)
				)
				(theGlobalSound fade: 0 15 10 1)
			)
			(10
				(if (< (ego y?) 153)
					(ego
						reset: 2
						setLoop: -1
						setMotion: PolyPath 150 170 self
					)
				else
					(self cue:)
				)
			)
			(11
				(ego reset: 2 setLoop: -1 setHeading: 180 self)
			)
			(12 (= seconds 4))
			(13
				(theGlobalSound number: 0 stop:)
				(theMusic pause: 0)
				(Bclr 59)
				(= local52 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)
