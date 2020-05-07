;;; Sierra Script 1.0 - (do not remove this comment)
(script# 340)
(include sci.sh)
(use Main)
(use rSacred)
(use genie)
(use n342)
(use n343)
(use nightMare)
(use NewRoomCue)
(use Kq6Procs)
(use Conv)
(use Scaler)
(use PolyPath)
(use Feature)
(use LoadMany)
(use Motion)
(use Actor)
(use System)

(public
	rm340 0
	theDoor 1
	labRock 2
)

(local
	local0
	local1
	local2
	local3
)
(instance cliffTalk of Conversation
	(properties)
)

(instance rm340 of KQ6Room
	(properties
		noun 3
		picture 340
		horizon 70
		walkOffEdge 1
	)
	
	(method (init)
		(super init: &rest)
		(Lock 143 340 0)
		(proc343_0)
		(features
			add: labDoor niteShade cave oracle myPath mount
			eachElementDo: #init
		)
		(if (Btst 1)
			(= local2 23)
			(minoOpening init:)
		else
			(= local2 20)
		)
		(cast
			add: theDoor labRock rockStep berries
			eachElementDo: #init
		)
		(theMusic number: 340 setLoop: -1 play:)
		(rockStep addToPic: dispose:)
		(ego actions: egoDoVerb init:)
		(cond 
			((== prevRoomNum 390) (curRoom setScript: crawlOutOfCave))
			((== prevRoomNum 405)
				(theGame handsOff:)
				(if (and (Btst 14) (Btst 4) (not (Btst 15)))
					(LoadMany 128 335 337 336)
					(theGlobalSound number: 345 play:)
					(Load rsSCRIPT 344)
					(proc344_0)
				)
				(curRoom setScript: fromLab)
			)
			((== prevRoomNum 440)
				(ego
					posn: 18 138
					view: 361
					setLoop: 0
					setPri: 9
					cel: 0
					normal: 0
					hide:
				)
				(if (not (Btst 3))
					(Load rsSCRIPT 342)
					(berries addToPic: dispose:)
					(theDoor addToPic: dispose:)
					(UnLoad 128 330)
					(proc342_0)
				else
					(theGame handsOff:)
					(if (and (Btst 14) (Btst 4) (not (Btst 15)))
						(LoadMany 128 335 337 336)
						(theGlobalSound number: 345 play:)
						(Load rsSCRIPT 344)
						(proc344_0)
					)
					(curRoom setScript: fromLab)
				)
			)
			((== prevRoomNum 370)
				(curRoom north: 0)
				(Load rsSCRIPT 342)
				(ego posn: 340 -10 reset: ignoreHorizon:)
				(proc342_1)
			)
			((and (not (Btst 1)) (Btst 2))
				(LoadMany 128 343 344)
				(Load rsSCRIPT 342)
				(berries addToPic: dispose:)
				(proc342_2)
			)
			((== prevRoomNum 350)
				(theGame handsOn:)
				(ego
					reset:
					posn: 232 77
					setScale: Scaler 100 5 105 65
					setMotion: MoveTo 236 122
				)
				((ego scaler?) doit:)
			)
			(else
				(cond 
					(
					(and (OneOf prevRoomNum 320 300) (not (Btst 4))) (LoadMany 128 334 330) (Load rsSCRIPT 341) (proc341_0))
					((and (Btst 14) (Btst 4) (not (Btst 15)))
						(LoadMany 128 335 337 336)
						(theGlobalSound number: 345 play:)
						(Load rsSCRIPT 344)
						(proc344_0)
					)
				)
				(ego
					view: 301
					loop: 6
					normal: 0
					posn: 219 199
					cycleSpeed: 10
					setPri: 15
					setScale: Scaler 100 5 105 65
				)
				(curRoom setScript: egoEnters)
			)
		)
		(Bset 157)
	)
	
	(method (doit)
		(cond 
			((curRoom script?))
			(local3)
			((== (ego onControl: 1) 16) (theMusic fade: 0 10 10) (curRoom newRoom: 405))
			((and (== (ego onControl: 1) 512) (Btst 1)) (theMusic fade: 0 10 10) (curRoom setScript: goToLair))
			((== (ego onControl: 1) 1024) (theMusic fade: 0 10 10) (curRoom setScript: stepDown))
			((== (ego onControl: 1) 2) (theGame handsOff:) (curRoom setScript: dieHard))
			((== (ego edgeHit?) 1)
				(theGame handsOff:)
				(theMusic fade: 0 10 10)
				(curRoom setScript: goNorth)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(LoadMany 0 341 342 344)
		(if (not (== (theGlobalSound number?) 155))
			(theGlobalSound fade: 0 5 5)
		)
		(rSacred marePresent: 0)
		(ego setScale: 0)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (Btst 1)
					(messager say: 3 1 23 1 self)
				else
					(messager say: 3 1 20 1 self)
				)
				1
			)
			(2
				(cond 
					((== (rSacred geniePresent?) 1) (messager say: 3 2 21 1))
					((== (rSacred marePresent?) 1) (messager say: 3 2 22 1))
					(else (messager say: 3 2 24 1))
				)
				1
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(cond 
			((== (rSacred geniePresent?) 1) (messager say: 3 1 21 1))
			((== (rSacred marePresent?) 1) (messager say: 3 1 22 1))
		)
	)
	
	(method (newRoom)
		(= local3 1)
		(super newRoom: &rest)
	)
	
	(method (notify)
		(proc344_1)
	)
	
	(method (scriptCheck &tmp temp0)
		(= temp0 1)
		(if (rSacred geniePresent?)
			(messager say: 0 0 4 1 0 899)
			(= temp0 0)
		)
		(return temp0)
	)
)

(instance rockStep of View
	(properties
		x 154
		y 185
		noun 6
		view 341
	)
)

(instance theDoor of Prop
	(properties
		x 113
		y 67
		noun 4
		view 340
		priority 7
		signal $7010
		cycleSpeed 10
	)
	
	(method (init)
		(if (Btst 3)
			(self cel: 4 stopUpd:)
		else
			(self cel: 0 stopUpd:)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(labDoor doVerb: theVerb &rest)
	)
)

(instance labRock of Actor
	(properties
		noun 5
		view 340
		loop 1
		signal $5000
	)
	
	(method (init)
		(if (Btst 3)
			(self posn: 0 149 stopUpd:)
		else
			(self posn: 9 137 stopUpd:)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (messager say: 5 1 local2 1))
			(5 (messager say: 5 5 local2 1))
			(2
				(super doVerb: theVerb &rest)
			)
			(else 
				(messager say: 5 0 local2 1)
			)
		)
	)
)

(instance berries of View
	(properties
		x 283
		y 112
		z 15
		noun 12
		view 330
		priority 7
		signal $6010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(theGame handsOff:)
				(curRoom setScript: gag_Die)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance labDoor of Feature
	(properties
		x 98
		y 166
		noun 4
		onMeCheck $0010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (messager say: 4 1 local2 1))
			(5
				(cond 
					((Btst 1) (messager say: 4 5 local2 1))
					((curRoom script?) 0)
					(else (curRoom setScript: tryDoor))
				)
			)
			(2
				(super doVerb: theVerb &rest)
			)
			(else 
				(messager say: 4 0 local2 1)
			)
		)
	)
)

(instance niteShade of Feature
	(properties
		x 295
		y 110
		noun 9
		onMeCheck $0004
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(cliffTalk add: -1 9 1 0 1 add: -1 9 1 0 2 init:)
			)
			(5
				(if (== (rSacred geniePresent?) 1)
					(messager say: 9 5 21 0)
				else
					(messager say: 9 5 27 1)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance minoOpening of Feature
	(properties
		x 18
		y 131
		onMeCheck $0040
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 5)
			(curRoom setScript: goToLair)
		else
			(labRock doVerb: theVerb)
		)
	)
)

(instance cave of Feature
	(properties
		x 307
		y 106
		noun 7
		onMeCheck $0008
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 5)
			(if (== (rSacred geniePresent?) 1)
				(messager say: 7 5 21 1)
			else
				(curRoom setScript: crawlIntoCave)
			)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance mount of Feature
	(properties
		x 256
		y 41
		noun 13
		onMeCheck $0020
	)
)

(instance oracle of Feature
	(properties
		x 258
		y 15
		noun 14
		onMeCheck $0080
	)
)

(instance myPath of Feature
	(properties
		x 232
		y 78
		noun 15
		onMeCheck $1000
	)
)

(instance egoEnters of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (== prevRoomNum 320)
					(soundFx number: 341 setLoop: -1 play:)
				)
				(ego
					view: 3011
					setLoop: 6
					setPri: 15
					cel: 0
					normal: 0
					posn: 257 237
					cycleSpeed: 8
					setCycle: End self
				)
			)
			(1
				(ego cel: 0 posn: 228 223 setCycle: End self)
			)
			(2
				(ego cel: 0 posn: 199 209 setCycle: End self)
			)
			(3
				(ego cel: 0 posn: 175 195 setCycle: End self)
			)
			(4
				(ego
					view: 3011
					setLoop: 1
					cel: 5
					posn: 164 194
					cycleSpeed: 8
					setCycle: End self
				)
			)
			(5
				(ego view: 301 setLoop: 0 cel: 2 normal: 0 posn: 161 185)
				(= cycles 4)
			)
			(6
				(ego cel: 3 posn: 161 185)
				(= cycles 4)
			)
			(7
				(ego cel: 4 posn: 161 185)
				(= cycles 4)
			)
			(8
				(ego
					posn: 162 169
					reset: 6
					setScale: Scaler 100 5 105 65
					setMotion: MoveTo 172 160 self
				)
			)
			(9 (= cycles 6))
			(10
				(cond 
					((and (== prevRoomNum 320) (not (Btst 4))) (messager say: 1 0 1 1) (proc341_1))
					((== prevRoomNum 300) (theGame handsOn:) (messager say: 1 0 33 1))
					(else (theGame handsOn:))
				)
				(self dispose:)
			)
		)
	)
)

(instance fromLab of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== prevRoomNum 405)
					(ego
						posn: 109 107
						setScale: Scaler 100 5 105 65
						setMotion: PolyPath 138 122 self
					)
				else
					(ego
						show:
						ignoreActors: 1
						setScale: Scaler 100 5 105 65
						setPri: 8
						cycleSpeed: 12
						setCycle: End self
					)
				)
			)
			(1
				(if (== prevRoomNum 405)
					(self cue:)
				else
					(ego reset: 0 posn: 22 138 setMotion: MoveTo 63 148 self)
				)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance crawlOutOfCave of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 331
					setLoop: 3
					normal: 0
					posn: 331 108
					cycleSpeed: 8
					moveSpeed: 8
					setCycle: Fwd
					setMotion: MoveTo 294 116 self
				)
			)
			(1
				(ego setLoop: 5 cel: 0 setCycle: End self)
			)
			(2
				(theGame handsOn:)
				(ego
					posn: (- (ego x?) 12) (+ (ego y?) 3)
					reset: 5
					setScale: Scaler 100 5 105 65
				)
				(self dispose:)
			)
		)
	)
)

(instance crawlIntoCave of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 283 121 self)
			)
			(1 (= cycles 6))
			(2
				(if (== (rSacred geniePresent?) 1)
					(ego setScript: 0)
					(proc341_3)
				)
				(if (Btst 96)
					(messager say: 7 5 32 1 self)
				else
					(messager say: 7 5 31 1 self)
				)
			)
			(3
				(ego setHeading: 45)
				(= cycles 6)
			)
			(4
				(if (== (rSacred geniePresent?) 1) (proc341_2))
				(ego
					setScale: 0
					view: 331
					normal: 0
					setLoop: 1
					cel: 5
					cycleSpeed: 10
					setPri: 7
					posn: 285 133
					setCycle: Beg self
				)
			)
			(5
				(DisposeScript 341)
				(ego
					setLoop: 2
					cel: 0
					cycleSpeed: 8
					moveSpeed: 8
					posn: 290 117
					setCycle: Fwd
					setMotion: MoveTo 331 106 self
				)
			)
			(6
				(cast eachElementDo: #hide)
				(curRoom drawPic: 98 -32758)
				(= cycles 6)
			)
			(7
				(theGame handsOn:)
				(curRoom newRoom: 390)
			)
		)
	)
)

(instance goNorth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== (rSacred geniePresent?) 1)
					(self setScript: (ScriptID 341 5) self)
				else
					(= cycles 6)
				)
			)
			(1 (curRoom newRoom: 350))
		)
	)
)

(instance goToLair of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 35 140 self)
			)
			(1
				(ego setHeading: 315)
				(= cycles 10)
			)
			(2
				(ego
					view: 362
					setLoop: 3
					cel: 0
					setPri: 9
					normal: 0
					posn: 15 146
					cycleSpeed: 12
					setCycle: End self
				)
			)
			(3 (curRoom newRoom: 440))
		)
	)
)

(instance tryDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 127 118 self)
			)
			(1
				(ego setMotion: PolyPath 127 113 self)
			)
			(2
				(ego
					view: 361
					loop: 1
					normal: 0
					posn: 119 115
					cycleSpeed: 10
					setCycle: End self
				)
			)
			(3 (ego setCycle: Beg self))
			(4
				(ego reset: 7 posn: 127 113)
				(= ticks 18)
			)
			(5
				(messager say: 4 5 20 1 self)
			)
			(6
				(messager say: 4 5 20 2 self)
			)
			(7
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance stepDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: 0 posn: 162 169)
				(if (== (rSacred geniePresent?) 1)
					(self setScript: (ScriptID 341 5) self)
				else
					(= cycles 6)
				)
			)
			(1
				(ego view: 301 setLoop: 8 cel: 6 normal: 0 posn: 152 170)
				(= cycles 4)
			)
			(2
				(ego cel: 0 normal: 0 posn: 168 177)
				(= cycles 4)
			)
			(3
				(ego cel: 5 normal: 0 posn: 151 170)
				(= cycles 4)
			)
			(4
				(ego cel: 2 normal: 0 posn: 165 178)
				(= cycles 4)
			)
			(5
				(ego
					view: 301
					setPri: 15
					cycleSpeed: 8
					setLoop: 8
					cel: 0
					normal: 0
					posn: 178 184
					setCycle: End self
				)
			)
			(6
				(ego cel: 0 normal: 0 posn: 198 197 setCycle: End self)
			)
			(7
				(ego cel: 0 normal: 0 posn: 218 209 setCycle: End self)
			)
			(8
				(ego cel: 0 normal: 0 posn: 239 220 setCycle: End self)
			)
			(9 (ego y: 280) (= cycles 4))
			(10 (curRoom newRoom: 300))
		)
	)
)

(instance gag_Die of Script
	(properties
		name "gag&Die"
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (rSacred geniePresent?)
					((ScriptID 341 4) setScript: 0)
				)
				(ego setScript: 0)
				(if (== (rSacred geniePresent?) 1)
					(= local0 21)
				else
					(= local0 27)
				)
				(messager say: 12 5 local0 1 self)
			)
			(1
				(ego setMotion: PolyPath 271 118 self)
			)
			(2
				(ego
					view: 331
					setLoop: 4
					cel: 0
					normal: 0
					cycleSpeed: 10
					setCycle: End self
				)
			)
			(3
				(messager say: 12 5 local0 2 self)
			)
			(4
				(messager say: 12 5 local0 3 self)
			)
			(5
				(if (== (rSacred geniePresent?) 1) (proc341_3))
				(soundFx stop:)
				(soundFx2 number: 343 setLoop: 1 play:)
				(ego
					view: 332
					loop: 1
					cel: 0
					posn: 247 128
					setCycle: End self
				)
			)
			(6
				(if (== (rSacred geniePresent?) 1) (proc341_2))
				(= cycles 6)
			)
			(7
				(DisposeScript 341)
				(= cycles 6)
			)
			(8
				(messager say: 12 5 local0 4 self)
			)
			(9 (EgoDead 20))
		)
	)
)

(instance dieHard of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1 temp2)
		(switch (= state newState)
			(0
				(= temp1 0)
				(messager say: 3 3 17 1 self 340)
			)
			(1
				(soundFx2 number: 305 setLoop: 1 play:)
				(ego
					setLoop: 2
					normal: 0
					cycleSpeed: 1
					setPri: 15
					setCycle: Fwd
				)
				(= seconds 3)
			)
			(2
				(soundFx2 number: 306 setLoop: 1 play: self)
				(curRoom walkOffEdge: 1)
				(ego
					setLoop: 2
					setStep: -1 15
					setMotion: MoveTo (ego x?) 300 self
				)
			)
			(3 0)
			(4 (= seconds 4))
			(5
				(soundFx2 number: 307 setLoop: 1 play:)
				(ShakeScreen 3 1)
				(= seconds 3)
			)
			(6
				(messager say: 3 3 17 2 self 340)
			)
			(7 (EgoDead 8))
		)
	)
)

(instance egoDoVerb of Actions
	(properties)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(1 (return 0))
				(5 (return 0))
				(2 (return 0))
				(31
					(if (and (Btst 14) (Btst 4) (not (Btst 15)))
						(theGame handsOff:)
						((ScriptID 344 2) setScript: (ScriptID 344 3))
						(return 1)
					else
						(return 0)
					)
				)
				(else  (return 0))
			)
		)
	)
)
