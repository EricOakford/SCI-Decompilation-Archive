;;; Sierra Script 1.0 - (do not remove this comment)
(script# 220)
(include sci.sh)
(use Main)
(use rgCrown)
(use walkEgoInScr)
(use KQ6Print)
(use KQ6Room)
(use Kq6Procs)
(use Conv)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use StopWalk)
(use DPath)
(use Reverse)
(use Grooper)
(use Motion)
(use Actor)
(use System)

(public
	rm220 0
	guardOpenDoorScr 1
	guardCloseDoorScr 2
	guard1 3
	guard2 4
	castleDoor 5
	saladin 6
	secondGuardDoorScr 7
)

(local
	local0
	local1
	local2
	local3 =  1
	local4
)
(instance rm220 of KQ6Room
	(properties
		noun 3
		picture 220
		horizon 0
		south 210
		west 230
	)
	
	(method (init)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 0
					init: 60 229 -99 229 -99 124 119 122 150 135 154 149 54 189
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 30 138 134 138 134 149 30 149
					yourself:
				)
				((Polygon new:)
					type: 3
					init:
						-100
						289
						419
						289
						419
						176
						281
						178
						281
						168
						285
						168
						285
						163
						270
						163
						270
						167
						276
						167
						276
						178
						223
						165
						215
						158
						235
						150
						212
						145
						237
						133
						202
						126
						233
						116
						216
						110
						175
						116
						93
						116
						82
						112
						-100
						112
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 182 229 182 185 235 185 235 229
					yourself:
				)
		)
		(LoadMany 128 725 224 220)
		(if (ego has: 5) (LoadMany 128 221 733))
		(super init: &rest)
		(Bset 162)
		(ego
			init:
			actions: egoDoVerbCode
			reset: 3
			setScale: Scaler 100 94 189 95
		)
		(switch prevRoomNum
			(150 (ego reset: 2 hide:))
			(230 (proc12_1 12 118))
			(else  (proc12_1 155 184 38))
		)
		(castleDoor init:)
		(guard1 init:)
		(guard2 init:)
		(genericFeatures init:)
		(guardHut init:)
		((ScriptID 10 4) onMeCheck: 1024 init:)
		(backOffScr client: guard1)
		(cond 
			((== prevRoomNum 150) (self setScript: (ScriptID 221 0)))
			(((ScriptID 10 0) isSet: 2)
				(= local2 1)
				(proc10_2 (ScriptID 224 0))
				(if (and (== currentAct 3) (not (Btst 18)))
					(Bset 135)
				)
			)
			(
				(or
					(and
						(== currentAct 3)
						(or (not (Btst 18)) (Btst 135))
					)
					(and (== currentAct 5) (ego has: 5) (not (Btst 18)))
				)
				(= local2 1)
				(proc10_2 (ScriptID 223 0))
				(Bclr 135)
			)
		)
		(theMusic number: 220 loop: -1 play:)
		(Bset 18)
	)
	
	(method (doit &tmp temp0)
		(= temp0 (InRect 106 110 140 118 (ego x?) (ego y?)))
		(if
		(and (== local2 1) ((ScriptID 10 0) isSet: 4096))
			(= local2 0)
			(ego setHeading: 0)
		)
		(cond 
			(script 0)
			(
				(and
					(or (and temp0 (not local0)) (and (not temp0) local0))
					(not (guard1 script?))
					(not (guard2 script?))
				)
				(curRoom setScript: guardStanceScr)
			)
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(super dispose: &rest)
		(DisposeScript 964)
		(DisposeScript 969)
		(if (== newRoomNum 210)
			(theMusic fadeTo: 917 -1)
		else
			(theMusic fade:)
		)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(messager say: noun theVerb (if (Btst 64) 23 else 22))
			1
		else
			(super doVerb: theVerb)
		)
	)
	
	(method (cue)
		(self setScript: 88)
	)
	
	(method (edgeToRoom param1)
		(switch param1
			(3 (proc12_0 param1 38) 0)
			(4 (proc12_0 param1) 0)
			(2
				(self setScript: bumpedTheEdgeScr)
				0
			)
			(else 
				(super edgeToRoom: param1 &rest)
			)
		)
	)
	
	(method (scriptCheck param1 &tmp temp0)
		(return
			(switch param1
				(87
					(messager say: 0 0 1 0 0 899)
					(return 1)
				)
				(88
					(if (ego inRect: 252 148 308 178)
						(messager say: 7 0 16 1 0 0 0)
						(return 0)
					else
						(return 1)
					)
				)
				(else  (return 1))
			)
		)
	)
)

(instance egoDoVerbCode of Actions
	(properties)
	
	(method (doVerb theVerb &tmp temp0)
		(= temp0 1)
		(switch theVerb
			(45
				(curRoom setScript: wearClothingScr)
			)
			(else  (= temp0 0))
		)
		(return temp0)
	)
)

(instance roomConv of Conversation
	(properties)
)

(instance bumpedTheEdgeScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff: 1)
				(= cycles 2)
			)
			(1
				(messager say: 1 0 24 0 self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance guardCloseDoorScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(guard1
					view: 725
					setLoop: GradualLooper
					setCycle: Walk
					setMotion: MoveTo 108 97 self
				)
			)
			(1 (guard1 setHeading: 0 self))
			(2
				(castleDoor setCycle: BegLoop self)
			)
			(3
				(soundFx2 number: 223 loop: 1 play:)
				(guard1 setHeading: 180 self)
			)
			(4
				(guard1 setMotion: MoveTo 100 109 self)
			)
			(5 (guard1 setHeading: 90 self))
			(6
				(guard1 view: 224 setLoop: -1 loop: 0 cel: 0)
				(= cycles 2)
			)
			(7
				(guard1 stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance guardOpenDoorScr of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(= register 0)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(guard1
					view: 725
					setLoop: GradualLooper
					setCycle: Walk
					setMotion: MoveTo 112 105 self
				)
			)
			(1
				(guard1 setMotion: MoveTo 108 97 self)
			)
			(2 (guard1 setHeading: 0 self))
			(3
				(castleDoor setCycle: EndLoop self)
				(soundFx2 number: 222 loop: 1 play:)
			)
			(4
				(if (not register)
					(guard1 setMotion: MoveTo 108 92 self)
				else
					(guard1 setMotion: MoveTo 90 97 self)
				)
			)
			(5
				(if (not register)
					(guard1
						setPri: 2
						setScale: Scaler 70 100 103 95
						setMotion: MoveTo 75 100 self
					)
				else
					(guard1 setHeading: 90 self)
				)
			)
			(6 (client cue:))
			(7
				(if (not register)
					(guard1 setMotion: MoveTo 108 92 self)
					(= state (+ state 3))
				)
				(guard1 setMotion: MoveTo 108 97 self)
			)
			(8 (guard1 setHeading: 0 self))
			(9
				(castleDoor setCycle: BegLoop self)
			)
			(10
				(soundFx2 number: 223 loop: 1 play:)
				(guard1 setHeading: 180 self)
			)
			(11
				(guard1
					setScale: 0
					setPri: -1
					setMotion: MoveTo 100 109 self
				)
			)
			(12 (= cycles 2))
			(13
				(guard1 setHeading: 90 self)
			)
			(14
				(guard1 view: 224 setLoop: -1 loop: 0 cel: 0)
				(= cycles 2)
			)
			(15 (self dispose:))
		)
	)
)

(instance guardStanceScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (and local4 (not (ego isStopped:))) (-- state))
				(= cycles 2)
			)
			(1
				(= local4 0)
				(if (not local0)
					(guard1 loop: 2)
					(guard2 loop: 3)
					(guard1 setCycle: EndLoop)
					(guard2 setCycle: EndLoop self)
				else
					(guard1 setCycle: BegLoop)
					(guard2 setCycle: BegLoop self)
				)
			)
			(2
				(if
				(and (not local0) (not register) (not local1))
					(cond 
						((not (Btst 20)) (messager say: 4 3 8 1 self))
						(
							(or
								(and (== currentAct 1) (Btst 72))
								(> currentAct 1)
							)
							(messager say: 4 3 17 1 self)
						)
						(else (messager say: 4 3 18 1 self))
					)
				else
					(self cue:)
				)
				(= local1 0)
			)
			(3
				(if (== (guard1 cel?) 0)
					(guard1 loop: 0)
					(guard2 loop: 1)
					(= local0 0)
					(Bset 162)
				else
					(= local0 1)
					(Bclr 162)
				)
				(guard1 stopUpd:)
				(guard2 stopUpd:)
				(if register
					(theDoits
						add: (CueObj client: guard1 state: 1 cue: yourself:)
					)
				)
				(= cycles 2)
			)
			(4
				(= register 0)
				(self dispose:)
			)
		)
	)
)

(instance wearClothingScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					setMotion: PolyPath (guardHut approachX?) (guardHut approachY?) self
				)
			)
			(1 (= cycles 2))
			(2
				(messager say: 2 45 0 1 self)
			)
			(3
				(ego
					setSpeed: 6
					normal: 0
					view: 221
					loop: 1
					cel: 0
					setCycle: EndLoop self
				)
			)
			(4 (= cycles 1))
			(5
				(ego loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(6 (= cycles 1))
			(7
				(ego view: 733 normal: 1 setCycle: Walk)
				(= cycles 2)
			)
			(8
				(Bset 59)
				(messager say: 2 45 0 2 self)
			)
			(9
				(ego setMotion: MoveTo 277 181 self)
			)
			(10 (= cycles 2))
			(11
				(KQ6Print
					font: userFont
					posn: 195 65
					width: 100
					say: 0 2 45 0 3
					init: self
				)
			)
			(12
				(ego setMotion: PolyPath 125 118 self)
			)
			(13 (ego setHeading: 0 self))
			(14 (= ticks 45))
			(15
				(self setScript: secondGuardDoorScr self)
			)
			(16
				(messager say: 2 45 0 4 self)
			)
			(17
				(KQ6Print
					font: userFont
					posn: -1 20
					say: 0 2 45 0 5
					init: self
				)
			)
			(18
				(theGame givePoints: 4)
				(ego ignoreActors: 1 setMotion: DPath 125 110 107 93 self)
			)
			(19
				(ego
					setPri: 2
					setScale: Scaler 64 94 103 95
					moveSpeed: 8
					setMotion: MoveTo 75 100 self
				)
			)
			(20
				(ego hide:)
				(secondGuardDoorScr cue:)
			)
			(21 (curRoom newRoom: 730))
		)
	)
)

(instance secondGuardDoorScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(guard2
					view: 725
					posn: 139 108
					setLoop: GradualLooper
					loop: 1
					setCycle: Walk
					setMotion: DPath 120 108 108 97 self
				)
			)
			(1
				(castleDoor setCycle: EndLoop self)
				(soundFx2 number: 222 loop: 1 play:)
			)
			(2
				(guard2 setMotion: MoveTo 129 96 self)
			)
			(3
				(guard2 setHeading: 270 self)
			)
			(4 (= cycles 2))
			(5
				(guard2 stopUpd:)
				(client cue:)
			)
			(6
				(guard2 startUpd: setMotion: MoveTo 108 97 self)
			)
			(7 (guard2 setHeading: 0 self))
			(8 (= cycles 2))
			(9
				(castleDoor setCycle: BegLoop self)
			)
			(10
				(soundFx2 number: 223 loop: 1 play:)
				(guard2 setMotion: DPath 120 108 139 109 self)
			)
			(11
				(guard2 setHeading: 270 self)
			)
			(12 (= cycles 2))
			(13
				(guard2
					view: 224
					setLoop: -1
					loop: 1
					cel: 0
					posn: 139 109
				)
				(= cycles 2)
			)
			(14 (self dispose:))
		)
	)
)

(instance guardHutScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					setMotion: PolyPath (guardHut approachX?) (guardHut approachY?) self
				)
			)
			(1 (= cycles 2))
			(2 (ego setHeading: 0 self))
			(3 (= ticks 45))
			(4
				(ego setMotion: MoveTo 277 181 self)
			)
			(5 (messager say: 6 5 0 0 self))
			(6
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance castleDoor of Prop
	(properties
		x 107
		y 94
		noun 4
		approachX 122
		approachY 115
		view 220
		priority 1
		signal $0010
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 5 stopUpd:)
	)
	
	(method (handleEvent event)
		(= local4 0)
		(if (self onMe: event)
			(if
				(or
					(and (== (ego x?) approachX) (== (ego y?) approachY))
					(InRect 106 110 140 118 (ego x?) (ego y?))
				)
				(= local3 1)
			else
				(= local3 0)
			)
		)
		(super handleEvent: event &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(if (not local3) (= local4 1) (= local3 1) (return))
				(cond 
					((not (Btst 20)) (messager say: 4 3 8 1 self))
					(
						(or
							(and (== currentAct 1) (Btst 72))
							(> currentAct 1)
						)
						(messager say: 4 3 17 1 self)
					)
					(else (messager say: 4 3 18 1 self))
				)
			)
			(2 (messager say: noun theVerb))
			(35
				(messager say: noun theVerb)
			)
			(1
				(if (== cel 0)
					(messager say: noun 1 6)
				else
					(messager say: noun 1 7)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance genericCoinShowScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (Btst 64)
					(ego
						setSpeed: 6
						normal: 0
						view: 221
						loop: 0
						cel: 0
						setCycle: EndLoop self
					)
				else
					(self cue:)
				)
			)
			(1
				(messager say: 5 40 register 1 self)
			)
			(2
				(if (Btst 64)
					(= state (+ state 3))
					(self cue:)
				else
					(guardStanceScr register: 0)
					(= local1 1)
					(if
					(not (if (== (ego x?) 125) (== (ego y?) 118)))
						(ego setMotion: PolyPath 125 118 self)
					else
						(++ state)
						(self cue:)
					)
				)
			)
			(3 (= cycles 2))
			(4
				(if (== (curRoom script?) guardStanceScr) (-- state))
				(= cycles 2)
			)
			(5
				(ego
					setSpeed: 6
					normal: 0
					view: 221
					loop: 0
					cel: 0
					setCycle: EndLoop self
				)
			)
			(6
				(if (not (Btst 64))
					(messager say: 5 40 register 2 self)
				else
					(self cue:)
				)
			)
			(7 (ego setCycle: BegLoop self))
			(8
				(if (not (Btst 64))
					(messager say: 5 40 register 3 self)
				else
					(messager say: 5 40 register 2 self oneOnly: 0)
				)
			)
			(9
				(if (not (OneOf register 8 22))
					(self cue:)
				else
					(++ state)
					(backOffScr client: self cue:)
				)
			)
			(10
				(ego reset: 7)
				(= cycles 2)
			)
			(11
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance genericGiveAllScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					setSpeed: 6
					normal: 0
					view: 221
					loop: 0
					cel: 0
					setCycle: EndLoop self
				)
			)
			(1
				(= register 1)
				(messager
					say:
						5
						0
						(cond 
							((or (Btst 72) (> currentAct 1)) (= register 0) 17)
							((not (Btst 20)) 8)
							((and (== currentAct 1) (Btst 20)) 18)
						)
						0
						self
				)
			)
			(2 (ego setCycle: BegLoop self))
			(3
				(if register
					(self cue:)
				else
					(++ state)
					(backOffScr client: self cue:)
				)
			)
			(4 (ego reset: 7) (= cycles 1))
			(5
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance backOffScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego reset: 7)
				(= cycles 2)
				(theDoits add: self)
			)
			(1
				(ego
					setCycle: Reverse
					setLoop: 3
					setMotion: MoveTo 125 123 self
				)
			)
			(2 (ego reset: 3) (= cycles 2))
			(3
				(if (== (curRoom script?) guardStanceScr)
					((curRoom script?) caller: self)
				else
					(self cue:)
				)
			)
			(4 (= cycles 2))
			(5
				(theDoits delete: self)
				(= state -1)
				(if (not (== client guard1))
					(client cue:)
					(= client guard1)
				else
					(theGame handsOn:)
				)
			)
		)
	)
)

(instance notAct1NotNameScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset 72)
				(Bset 20)
				(ego setHeading: 0 self)
			)
			(1 (= cycles 2))
			(2
				(messager say: 5 register 12 1 self)
			)
			(3
				(if (== register 70)
					(ego
						normal: 0
						setSpeed: 6
						view: 221
						loop: 0
						cel: 0
						setCycle: EndLoop self
					)
				else
					(self cue:)
				)
			)
			(4 (= cycles 2))
			(5
				(roomConv add: -1 5 70 12 2 add: -1 5 70 12 3 init: self)
			)
			(6
				(if (== register 70)
					(ego setCycle: BegLoop self)
				else
					(= state (+ state 2))
					(= cycles 2)
				)
			)
			(7 (= cycles 2))
			(8 (ego reset: 3) (= cycles 2))
			(9
				(messager say: 5 70 12 4 self)
			)
			(10 (= cycles 2))
			(11
				(roomConv add: -1 5 70 12 5 add: -1 5 70 12 6 init: self)
			)
			(12 (= cycles 2))
			(13
				(backOffScr client: self cue:)
			)
			(14
				(messager say: 5 70 12 7 self)
			)
			(15
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance genericTalkScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (or (!= (ego loop?) 9) (!= (ego cel?) 3))
					(ego setHeading: 0 self)
				else
					(self cue:)
				)
			)
			(1 (= cycles 2))
			(2
				(if (and (== register 10) local0)
					(messager say: 5 2 register 2 self oneOnly: 0)
				else
					(messager say: 5 2 register 0 self)
				)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(class GateGuardDog of Actor
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 5
		modNum -1
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		sightAngle 26505
		actions 0
		onMeCheck $6789
		state $0000
		approachX 125
		approachY 118
		approachDist 0
		_approachVerbs 0
		yStep 2
		view 224
		loop 0
		cel 0
		priority 0
		underBits 0
		signal $5001
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		scaleSignal $0000
		scaleX 128
		scaleY 128
		maxScale 128
		cycleSpeed 6
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
		illegalBits $8000
		xLast 0
		yLast 0
		xStep 3
		origStep 770
		moveSpeed 6
		blocks 0
		baseSetter 0
		mover 0
		looper 0
		viewer 0
		avoider 0
		code 0
	)
	
	(method (init)
		(super init: &rest)
		(walkHandler addToFront: self)
		(self approachVerbs: 2)
	)
	
	(method (dispose)
		(super dispose:)
		(walkHandler delete: self)
	)
	
	(method (handleEvent event)
		(super handleEvent: event &rest)
		(if
			(and
				(self onMe: event)
				(not local0)
				(not (== (event message?) JOY_RIGHT))
				(& _approachVerbs (approachCode doit: (event message?)))
			)
			(guardStanceScr register: 1)
			(CueObj client: 0)
		else
			(guardStanceScr register: 0)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(ego setMotion: PolyPath mouseX (- mouseY 10))
			)
			(2
				(cond 
					((not (Btst 64)) (Bset 64) (self setScript: genericTalkScr 0 22))
					(
						(or
							(and (== currentAct 1) (Btst 72))
							(and (> currentAct 1) (Btst 20))
						)
						(self setScript: genericTalkScr 0 17)
					)
					((and (== currentAct 1) (Bset 19))
						(if ((ScriptID 10 0) isSet: 8192)
							(self setScript: genericTalkScr 0 16)
						else
							((ScriptID 10 0) setIt: 8192)
							(Bset 20)
							(self setScript: genericTalkScr 0 15)
						)
					)
					((== currentAct 1) (self setScript: genericTalkScr 0 10))
					(else (self setScript: notAct1NotNameScr 0 theVerb))
				)
			)
			(70
				(if (not (Btst 64))
					(messager say: noun theVerb 22)
				else
					(cond 
						(
						(or (Btst 72) (and (> currentAct 1) (Btst 20))) (self setScript: genericGiveAllScr))
						((and (== currentAct 1) (Btst 20)) (Bset 162) (self setScript: (ScriptID 222 0) 0 18))
						((== currentAct 1) (Bset 162) (self setScript: (ScriptID 222 0) 0 14))
						(else (self setScript: notAct1NotNameScr 0 theVerb))
					)
					(Bset 19)
					(Bset 20)
					((ScriptID 10 0) setIt: 8192)
				)
			)
			(5 (messager say: noun theVerb))
			(1
				(messager
					say:
						noun
						theVerb
						(cond 
							((not (Btst 20)) 8)
							((and (== currentAct 1) (Btst 72)) 17)
							((== currentAct 1) 18)
							(else 17)
						)
				)
			)
			(40
				(cond 
					((not (Btst 64)) (self setScript: genericCoinShowScr 0 22))
					((not (Btst 20)) (self setScript: genericCoinShowScr 0 8))
					(
						(or
							(and (== currentAct 1) (Btst 72))
							(> currentAct 1)
						)
						(self setScript: genericGiveAllScr)
					)
					((not (Btst 66)) (self setScript: genericCoinShowScr 0 20) (Bset 66))
					(else (messager say: noun theVerb 21))
				)
			)
			(else 
				(self setScript: genericGiveAllScr)
			)
		)
	)
	
	(method (onMe event &tmp temp0 temp1)
		(= temp0 (super onMe: event))
		(if (== (event message?) JOY_UPRIGHT)
			(= sightAngle 26505)
		else
			(= sightAngle 40)
		)
		(if temp0
			(= temp1 (approachCode doit: 0))
			(if
				(and
					(not (Btst 64))
					(== (theIconBar curIcon?) (theIconBar useIconItem?))
					(OneOf
						(theIconBar curInvIcon?)
						(inventory at: 39)
						(inventory at: 9)
					)
				)
				(self
					_approachVerbs: (& (~ temp1) (self _approachVerbs?))
				)
			else
				(self _approachVerbs: (| (self _approachVerbs?) temp1))
			)
		)
		(return temp0)
	)
)

(instance myHeadingCode of Code
	(properties)
	
	(method (doit param1 param2)
		(if
			(and
				(== param2 CueObj)
				((CueObj client?) isKindOf: GateGuardDog)
			)
			(= param1 0)
		)
		(if argc (ego heading: param1))
		(if (ego looper?)
			((ego looper?)
				doit: ego (ego heading?) (if (>= argc 2) param2 else 0)
			)
		else
			(DirLoop ego (ego heading?))
			(if (and (>= argc 2) (IsObject param2))
				(param2 cue: &rest)
			)
		)
		(return param1)
	)
)

(instance guard1 of GateGuardDog
	(properties
		x 100
		y 109
	)
)

(instance guard2 of GateGuardDog
	(properties
		x 139
		y 109
		sightAngle 180
		loop 1
	)
)

(instance saladin of Actor
	(properties
		x 75
		y 100
		view 736
		loop 2
		xStep 4
	)
	
	(method (init)
		(super init: &rest)
		(self
			setScale: Scaler 64 94 103 95
			setPri: 1
			setLoop: GradualLooper
			setCycle: StopWalk -1
			ignoreActors:
		)
	)
	
	(method (cue param1)
		(if (not argc) (= param1 0))
		(self
			setPri: 1
			setScale: Scaler 64 94 103 95
			moveSpeed: 8
			setMotion: MoveTo 75 100 param1
		)
	)
)

(instance guardHut of Feature
	(properties
		x 280
		y 170
		noun 6
		sightAngle 40
		onMeCheck $4000
		approachX 279
		approachY 165
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(45
				(curRoom setScript: wearClothingScr)
			)
			(5
				(curRoom setScript: guardHutScr)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance genericFeatures of Feature
	(properties
		sightAngle 40
	)
	
	(method (onMe event)
		(genericFeatures x: (event x?) y: (event y?))
		(= noun
			(switch (OnControl 4 (event x?) (event y?))
				(8192 8)
				(4096 11)
				(2048 9)
				(512 12)
				(else  0)
			)
		)
	)
)
