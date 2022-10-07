;;; Sierra Script 1.0 - (do not remove this comment)
(script# 250)
(include sci.sh)
(use Main)
(use walkEgoInScr)
(use KQ6Room)
(use Kq6Procs)
(use Conv)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)
(use System)

(public
	rm250 0
)

(local
	local0 =  -1
	[local1 2]
	local3
	local4
)
(procedure (localproc_149a)
	(return (== (beauty script?) roseTendingScr))
)

(procedure (localproc_14a9)
	(return (if (localproc_149a) (if (Btst 37) 10 else 12) else 11))
)

(instance rm250 of KQ6Room
	(properties
		noun 3
		picture 250
		horizon 0
		east 240
		south 240
		west 260
	)
	
	(method (init)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init: 369 -60 369 169 319 169 65 110 0 110 0 -60
					yourself:
				)
				((Polygon new:)
					type: 0
					init: -40 125 40 125 201 189 201 229 -40 229
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 0 161 84 162 103 186 176 186 176 189 0 189
					yourself:
				)
		)
		(ego init: setScale: Scaler 100 67 153 109)
		(super init: &rest)
		(if (== prevRoomNum 260)
			(proc12_1 8 116)
		else
			(proc12_1 272 187 -50)
		)
		(houseDoor init:)
		(gate init:)
		(genericFeatures init:)
		(houseRoses init:)
		(palmTrees init:)
		(featureToBeIgnored init:)
		((ScriptID 10 4) onMeCheck: 8 init:)
		(leaves init:)
		(if (>= (theGame _detailLevel?) 2)
			(leaves setScript: (ScriptID 13 0))
		)
		(cond 
			((and (ego has: 37) (not (Btst 43))) (beauty init: 1))
			(
				(and
					(!= gCurrentAct currentAct)
					(<= global155 3)
					(not (Btst 43))
				)
				(beauty
					init: 0
					setScript:
						(if (OneOf (++ global155) 3 4)
							rmAct3and4Scr
						else
							rmAct1and2Scr
						)
				)
			)
		)
		(theMusic number: 250 loop: -1 play:)
	)
	
	(method (dispose)
		(= gCurrentAct currentAct)
		(Bclr 59)
		(super dispose: &rest)
		(DisposeScript 964)
		(DisposeScript 13)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(messager
				say: noun theVerb (if (< currentAct 5) 26 else 27)
			)
			1
		else
			(super doVerb: theVerb &rest)
		)
	)
	
	(method (edgeToRoom param1 &tmp temp0)
		(= temp0 1)
		(switch param1
			(3
				(proc12_0 param1 -50)
				(return 0)
			)
			(2 (proc12_0 3 -50) (return 0))
			(4 (proc12_0 param1) (return 0))
			(else 
				(= temp0 0)
				(super edgeToRoom: param1 &rest)
			)
		)
		(return (if temp0 (beauty setScript: 0) (theMusic fade:) else 0))
	)
	
	(method (scriptCheck param1 &tmp temp0 temp1)
		(= temp0 0)
		(switch param1
			(87
				(if (cast contains: beauty)
					(messager say: 0 0 2 0 0 899)
				else
					(= temp0 1)
				)
			)
			(85
				(cond 
					(
						(and
							(= temp1 (cast contains: beauty))
							(localproc_149a)
						)
						(CueObj state: 0 cycles: 0 client: beauty theVerb: 31)
						(ego
							setMotion: PolyPath (beauty approachX?) (beauty approachY?) CueObj
						)
					)
					(temp1 (beauty doVerb: 31 &rest))
					(else (= temp0 1))
				)
			)
			(93
				(if (cast contains: beauty)
					(messager say: 7 0 16 0 0 0)
				else
					(= temp0 1)
				)
			)
			(else  (= temp0 1))
		)
		(return temp0)
	)
)

(instance roomConv of Conversation
	(properties)
)

(instance rmAct1and2Scr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset 59)
				(client
					view: 253
					loop: 2
					cel: 0
					posn: 213 81
					setCycle: EndLoop self
				)
			)
			(1
				(client loop: 3 cel: 0 setCycle: EndLoop self)
			)
			(2
				(client cel: 0 posn: 202 88 setCycle: EndLoop self)
			)
			(3
				(client cel: 0 posn: 194 94 setCycle: EndLoop self)
			)
			(4
				(client loop: 4 cel: 0 posn: 191 96 setCycle: EndLoop self)
			)
			(5
				(= seconds (= register (Random 1 9)))
			)
			(6
				(client loop: 5 cel: 0 posn: 174 97 setCycle: EndLoop self)
			)
			(7
				(client loop: 6 cel: 0)
				(= seconds (- 10 register))
			)
			(8
				(houseDoor
					noun: 23
					view: 255
					loop: 0
					cel: 0
					setCycle: EndLoop self
				)
				(soundFx2 number: 901 loop: 1 play:)
			)
			(9 (= ticks 12))
			(10
				(houseDoor loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(11 (= cycles 2))
			(12
				(houseDoor loop: 2 cel: 0)
				(= cycles 2)
			)
			(13
				(if (== global155 1)
					(= register 4)
				else
					(= register 5)
				)
				(messager say: 1 0 register 1 self)
			)
			(14
				(messager say: 1 0 register 2 self)
			)
			(15
				(if (!= register 4)
					(houseDoor setCycle: EndLoop self)
				else
					(++ state)
					(= ticks 1)
				)
			)
			(16 (= cycles 2))
			(17
				(messager say: 1 0 register 3 self)
			)
			(18
				(if (== register 4)
					(houseDoor setCycle: EndLoop self)
				else
					(++ state)
					(= ticks 1)
				)
			)
			(19 (= cycles 2))
			(20
				(messager say: 1 0 register 4 self)
			)
			(21
				(if (!= register 4)
					(= cycles 1)
				else
					(messager say: 1 0 register 5 self)
				)
			)
			(22
				(client setScript: rmEndofScriptsScr)
			)
		)
	)
)

(instance rmAct3and4Scr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset 59)
				(client view: 253 loop: 4 cel: 7 posn: 191 96)
				(= cycles 1)
			)
			(1
				(= seconds (= register (Random 1 9)))
			)
			(2
				(client loop: 5 cel: 0 posn: 174 97 setCycle: EndLoop self)
			)
			(3
				(client loop: 6 cel: 0)
				(= seconds (- 10 register))
			)
			(4
				(houseDoor
					noun: 23
					view: 255
					loop: 0
					cel: 0
					setCycle: EndLoop self
				)
				(soundFx2 number: 901 loop: 1 play:)
			)
			(5 (= cycles 2))
			(6
				(if (== global155 3)
					(= register 6)
					(houseDoor loop: 1 cel: 0 setCycle: EndLoop self)
				else
					(= register 7)
					(= state (+ state 2))
					(= ticks 1)
				)
			)
			(7 (= cycles 2))
			(8
				(houseDoor loop: 2 cel: 0)
				(= cycles 2)
			)
			(9
				(messager say: 1 0 register 0 self)
			)
			(10
				(client setScript: rmEndofScriptsScr)
			)
		)
	)
)

(instance rmGiveRoseScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego put: 38 curRoomNum)
				(theGame handsOff:)
				((ScriptID 10 0) setIt: 128)
				(if (Bset 37)
					(= register 20)
				else
					(= register 19)
					(theGame givePoints: 2)
					(Bclr 47)
				)
				(beauty setScript: 0)
				(messager say: 6 71 register 1 self)
			)
			(1
				(if (== register 19)
					(messager say: 6 71 register 2 self)
				else
					(self cue:)
				)
			)
			(2
				(ego
					setSpeed: 6
					view: 251
					loop: 0
					cel: 0
					posn: 250 154
					normal: 0
					setCycle: CycleTo 4 1 self
				)
			)
			(3
				(beauty view: 251 loop: 1 cel: 0 setCycle: CycleTo 3 1 self)
			)
			(4
				(beauty setCycle: EndLoop self)
				(ego cel: 5)
			)
			(5
				(ego
					reset: 0
					setSpeed: 6
					posn: (beauty approachX?) (beauty approachY?)
				)
				(= seconds 2)
			)
			(6
				(beauty loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(7
				(messager
					say: 6 71 register (if (== register 19) 3 else 2) self
					oneOnly: 0
				)
			)
			(8
				(beauty setScript: roseTendingScr)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance roseTendingScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client view: 251 loop: 4 cel: 0)
				(= cycles 1)
			)
			(1 (= seconds (Random 2 5)))
			(2 (client setCycle: EndLoop self))
			(3 (= seconds (Random 2 5)))
			(4 (client setCycle: BegLoop self))
			(5 (= state 0) (= cycles 1))
		)
	)
)

(instance turnScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 0)
			(1
				(= register 1)
				(ego setHeading: register self)
			)
			(2 (self dispose:))
		)
	)
)

(instance rmGiveRingScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego put: 37)
				(theGame handsOff:)
				(theGame givePoints: 2)
				(beauty
					setScript: 0
					setPri: 2
					view: 252
					posn: 290 158
					setHeading: 270 self
				)
				(= register 0)
			)
			(1
				(roomConv
					add: -1 6 69 10 1
					add: -1 6 69 10 2
					add: -1 6 69 10 3
					add: -1 6 69 10 4
					add: -1 6 69 10 5
					add: -1 6 69 10 6
					add: -1 6 69 10 7
					add: -1 6 69 10 8
					add: -1 6 69 10 9
					add: -1 6 69 10 10
					add: -1 6 69 10 11
					add: -1 6 69 10 12
					add: -1 6 69 10 13
					add: -1 6 69 10 14
					add: -1 6 69 10 15
					init: self
				)
			)
			(2
				(beauty
					view: 252
					setCycle: Walk
					setMotion: MoveTo 147 122 self
				)
				(self setScript: turnScr 0 90)
				(ego setMotion: MoveTo 121 154 turnScr)
			)
			(3
				(beauty hide:)
				(soundFx2 number: 906 loop: 1 play:)
				(gate view: 253 loop: 1 cel: 0 setCycle: CycleTo 5 1 self)
			)
			(4
				(gate setCycle: EndLoop)
				(beauty
					show:
					setPri: -1
					posn: 110 131
					setMotion: MoveTo 159 158 self
				)
			)
			(5
				(if (not (turnScr register?))
					(turnScr caller: self)
				else
					(= cycles 2)
				)
			)
			(6
				(beauty loop: 1)
				(ego loop: 0)
				(= cycles 2)
			)
			(7
				(roomConv
					add: -1 6 69 10 16
					add: -1 6 69 10 17
					add: -1 6 69 10 18
					init: self
				)
			)
			(8
				(ego
					normal: 0
					setSpeed: 6
					view: 253
					loop: 10
					posn: 127 157
					setCycle: CycleTo 4 1 self
				)
			)
			(9
				(beauty
					view: 253
					loop: 0
					cel: 0
					posn: 158 157
					setCycle: CycleTo 3 1 self
				)
			)
			(10
				(beauty setCycle: EndLoop self)
				(ego setCycle: EndLoop self)
			)
			(11 0)
			(12
				(messager say: 6 69 10 19 self)
			)
			(13
				(beauty
					view: 252
					setCycle: Walk
					cycleSpeed: 5
					moveSpeed: 5
					setMotion: MoveTo 320 180
				)
				(ego
					reset: 0
					posn: 121 154
					setSpeed: 9
					setMotion: MoveTo 300 190 self
				)
				(= ticks 60)
			)
			(14
				(houseDoor view: 255 loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(15
				(messager say: 6 69 10 20 self)
			)
			(16 (= cycles 10))
			(17
				(messager say: 6 69 10 21 self)
			)
			(18
				(theMusic fade:)
				(curRoom newRoom: 540)
			)
		)
	)
)

(instance rmEndofScriptsScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(houseDoor
					noun: 19
					view: 255
					loop: 0
					cel: (houseDoor lastCel:)
					setCycle: BegLoop houseDoor
				)
				(client posn: 174 97 loop: 7 cel: 0 setCycle: EndLoop self)
			)
			(1
				(client
					setLoop: 8
					posn: 189 85
					setCycle: Walk
					setMotion: MoveTo 216 66 self
				)
			)
			(2
				(houseDoor setCycle: EndLoop self)
				(soundFx2 number: 901 loop: 1 play:)
			)
			(3
				(client setLoop: -1 loop: 9 cel: 0 setCycle: EndLoop self)
			)
			(4
				(houseDoor setCycle: BegLoop self)
			)
			(5
				(soundFx2 number: 902 loop: 1 play:)
				(Bclr 59)
				(client dispose:)
			)
		)
	)
)

(instance useFluteOnBeautyScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= register (localproc_14a9))
				(if (!= register 11) (++ state))
				(= cycles 2)
			)
			(1
				(self setScript: (ScriptID 85) self)
			)
			(2
				(switch register
					(11
						(messager say: 1 31 0 0 self 0)
					)
					(10
						(messager say: 6 31 12 1 self)
					)
					(else 
						(++ state)
						(messager say: 6 31 register 0 self)
					)
				)
			)
			(3
				(messager
					say: (if (== register 10) 6 else 2) 31 register 0 self
				)
			)
			(4
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance giveAllScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= register (localproc_14a9))
				(messager
					say: 6 0 (if (== register 12) 10 else register) 1 self
				)
			)
			(1
				(if (!= register 11)
					(messager
						say: 6 0 register (if (== register 12) 1 else 2) self
					)
				else
					(= cycles 2)
				)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance scriptToBeIgnored of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(client view: 255 loop: 0 cel: 0)
				(= cycles 2)
			)
			(1 (client setCycle: EndLoop self))
			(2 (= cycles 2))
			(3
				(client view: 257 loop: 0 cel: 0 setCycle: EndLoop self)
			)
			(4 (= cycles 2))
			(5
				(client loop: 1 cel: 0 setCycle: Forward)
				(= ticks 300)
			)
			(6
				(client loop: 0 cel: 10 setCycle: BegLoop self)
			)
			(7 (= cycles 2))
			(8
				(client view: 255 loop: 0 cel: 3 setCycle: BegLoop self)
			)
			(9
				(client view: 250 loop: 1 cel: 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance beauty of Actor
	(properties
		x 214
		y 81
		noun 6
		sightAngle 45
		view 252
		signal $5000
	)
	
	(method (init param1)
		(super init: &rest)
		(if param1
			(self
				view: 251
				posn: 285 156
				loop: 4
				setPri: 5
				approachX: 247
				approachY: 153
				approachVerbs: 2 71
				setScale: Scaler 100 67 153 109
				setScript: roseTendingScr
			)
		else
			(self setCycle: Walk)
		)
	)
	
	(method (doVerb theVerb)
		(cond 
			(
				(and
					(OneOf theVerb 42 27 28 7 32 12 65 33)
					(localproc_149a)
				)
				(messager say: noun theVerb 18)
			)
			(
				(and
					(OneOf theVerb 57 58 59 60 96 43 56)
					(localproc_149a)
				)
				(messager say: noun 43 (if (Btst 37) 10 else 12))
			)
			((and (OneOf theVerb 66 70) (localproc_149a)) (messager say: noun 66 (if (Btst 37) 10 else 12)))
			((== theVerb 31) (curRoom setScript: useFluteOnBeautyScr))
			(
			(and (OneOf theVerb 74 73 55) (localproc_149a)) (messager say: noun 55 18))
			((and (== theVerb 71) (localproc_149a)) (curRoom setScript: rmGiveRoseScr))
			((== theVerb 69)
				(if (Btst 37)
					(curRoom setScript: rmGiveRingScr)
				else
					(messager say: noun theVerb 12)
				)
			)
			((== theVerb 2)
				(messager
					say:
						noun
						theVerb
						(if (localproc_149a)
							(cond 
								((Btst 37) (+ 22 (if (< local0 3) (++ local0) else local0)))
								((Btst 47) 14)
								(else 13)
							)
						else
							11
						)
				)
				(Bset 47)
			)
			((== theVerb 1)
				(messager
					say: noun theVerb (if (Bset 70) (if (Btst 37) 10 else 9) else 21)
				)
			)
			((== theVerb 5)
				(messager
					say: noun theVerb (if (localproc_149a) 18 else 11)
				)
			)
			(else (curRoom setScript: giveAllScr))
		)
	)
)

(instance gate of Prop
	(properties
		x 118
		y 108
		noun 7
		sightAngle 90
		view 250
		signal $4000
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 2) (++ local4))
		(super doVerb: theVerb &rest)
	)
)

(instance houseDoor of Prop
	(properties
		x 221
		y 68
		noun 19
		sightAngle 90
		view 250
		loop 1
		priority 1
		signal $0010
	)
	
	(method (doVerb theVerb)
		(if
		(and (== local4 3) (== local3 7) (== theVerb 2))
			(= local4 0)
			(= local3 0)
			(self setScript: scriptToBeIgnored)
		else
			(super doVerb: theVerb &rest)
		)
	)
	
	(method (cue)
		(self view: 250 loop: 1 cel: 0)
		(soundFx2 number: 902 loop: 1 play:)
	)
)

(instance leaves of Prop
	(properties
		x 60
		y 181
		view 256
		priority 15
		signal $5010
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance featureToBeIgnored of Feature
	(properties
		x 100
		y 320
		noun 3
		nsLeft 3
		nsBottom 7
		nsRight 9
	)
	
	(method (init)
		(super init: &rest)
		(= sightAngle 26505)
	)
	
	(method (doVerb theVerb)
		(if (== local4 3)
			(switch theVerb
				(5 (= local3 (| local3 $0001)))
				(2 (= local3 (| local3 $0002)))
				(1 (= local3 (| local3 $0004)))
			)
		)
		(super doVerb: theVerb &rest)
	)
)

(instance palmTrees of Feature
	(properties
		noun 15
		onMeCheck $0004
	)
)

(instance houseRoses of Feature
	(properties
		noun 22
		onMeCheck $1000
	)
)

(instance genericFeatures of Feature
	(properties
		sightAngle 40
	)
	
	(method (onMe event &tmp temp0)
		(= noun
			(switch (= temp0 (OnControl 4 (event x?) (event y?)))
				(2 8)
				(1024 21)
				(16 12)
				(32 11)
				(64 17)
				(128 14)
				(256 13)
				(512 18)
				(else  0)
			)
		)
	)
)
