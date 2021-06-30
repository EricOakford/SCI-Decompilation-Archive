;;; Sierra Script 1.0 - (do not remove this comment)
(script# 720)
(include game.sh) (include "720.shm")
(use Main)
(use LBRoom)
(use Inset)
(use Scaler)
(use Osc)
(use PolyPath)
(use Feature)
(use LoadMany)
(use StopWalk)
(use Timer)
(use Sound)
(use Motion)
(use Invent)
(use Actor)
(use System)

(public
	rm720 0
)

(local
	local0
	local1
)
(instance rm720 of LBRoom
	(properties
		noun N_ROOM
		picture 720
		north 730
		west 715
		vanishingY -10
	)
	
	(method (init &tmp temp0)
		(curRoom obstacles: (List new:))
		((ScriptID 2720 0) doit: (curRoom obstacles?))
		(ego init: normalize: 831 setScale: 145)
		(self setScript: sEnterWest)
		(Load RES_VIEW 722)
		(LoadMany RES_SOUND 48 721 722 723 736 17)
		(if (not (ego has: 12))
			(boot init: approachVerbs: 4 1 8)
		)
		((= temp0 (Inventory at: iLantern)) cel: 0)
		(temp0 cursor: 84)
		(super init:)
		(theMusic number: 720 flags: 1 loop: -1 play:)
		(steve init:)
		(stone init:)
		(maintDoor init: approachVerbs: 4)
		(furnaceDoor init: approachVerbs: 4)
		(stairs init: setOnMeCheck: 1 2)
		(junk1 init: setOnMeCheck: 1 4)
		(junk2 init: setOnMeCheck: 1 8)
		(coal init: setOnMeCheck: 1 16)
		(coalOnFace init:)
		(coalOnFaceFeat init:)
		(smellHere init:)
		(shovel1 init: setOnMeCheck: 1 32)
		(coalShute init: setOnMeCheck: 1 64)
		(longPipe init: setOnMeCheck: 1 128)
		(bigPipes init: setOnMeCheck: 1 256)
		(drain init: setOnMeCheck: 1 512)
		(shovel2 init: setOnMeCheck: 1 1024)
		(tunnel init: setOnMeCheck: 1 2048)
		(pipe1 init: setOnMeCheck: 1 4096)
		(pipe2 init: setOnMeCheck: 1 8192)
		(furnace init: setOnMeCheck: 1 16384)
		(flames init:)
		(light1 init:)
		(light2 init:)
		(furnaceGrate init:)
	)
	
	(method (dispose)
		(if (walkHandler contains: tunnel)
			(walkHandler delete: tunnel)
		)
		(DisposeScript 2720)
		(theMusic fade:)
		(super dispose:)
	)
	
	(method (cue)
		(cond 
			((self script?) (rileyTimer setReal: self 10) (self timer: rileyTimer))
			(local0
				(theMusic2 number: 3 loop: 1 flags: 1 play:)
				(self setScript: sO_RileyEnters)
			)
			(else (self setScript: sGunShots))
		)
	)
	
	(method (newRoom)
		(rileyTimer dispose: delete:)
		(super newRoom: &rest)
	)
)

(instance sGunShots of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(sFX number: 52 loop: 1 flags: 1 play: self)
			)
			(1 (= ticks 30))
			(2 (sFX play: self))
			(3
				(messager say: 22)
				(rileyTimer setReal: curRoom 30)
				(curRoom timer: rileyTimer)
				(theMusic2 number: 17 loop: -1 flags: 1 play:)
				(Load RES_SOUND 3)
				(= local0 1)
				(self dispose:)
			)
		)
	)
)

(instance sEnterWest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 723
					setScale: Scaler 100 100 0 10
					setLoop: 2
					posn: 0 161
					setMotion: MoveTo 54 187 self
				)
			)
			(1
				(rileyTimer setReal: curRoom 0 2)
				(curRoom timer: rileyTimer)
				(ego normalize: 831 loop: 6 setScale: 145)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sO_RileyEnters of Script
	(properties
		name "sO'RileyEnters"
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(o_Riley
					init:
					setLoop: 7
					setCycle: Walk
					setMotion: MoveTo 62 189 self
				)
				(if (< (ego x?) 120)
					(ego
						cycleSpeed: 4
						moveSpeed: 4
						setMotion: PolyPath 142 165 self
					)
				else
					(= cycles 1)
				)
			)
			(1)
			(2
				(o_Riley setPri: -1)
				(ego cycleSpeed: 6 moveSpeed: 6 setHeading: 270 self)
				(if (== (steve view?) 812) (steve setHeading: 270))
			)
			(3
				(o_Riley setLoop: 5 setCel: 0 setCycle: EndLoop self)
			)
			(4
				(o_Riley setLoop: 6 setCel: 0 setCycle: CycleTo 1 1 self)
			)
			(5
				(sFX number: 52 loop: 1 flags: 1 play:)
				(o_Riley setCycle: CycleTo 1 1 self)
			)
			(6
				(sFX play:)
				(o_Riley setCycle: CycleTo 0 1 self)
			)
			(7
				(ego
					view: 722
					setLoop: 4
					setCel: 0
					cycleSpeed: 12
					setScale: Scaler 100 100 0 10
					setCycle: EndLoop self
				)
			)
			(8
				(cond 
					(
						(or
							(== (steve view?) 812)
							(and
								(== (steve view?) 722)
								(== (steve loop?) 0)
								(== (steve cel?) 7)
							)
						)
						(self setScript: sKillSteve self)
					)
					((== (steve view?) 721) (self setScript: sKillCoalSteve self))
					((== (steve view?) 722) (self setScript: sKillNailSteve self))
				)
			)
			(9 (= ticks 120))
			(10
				(= deathReason 10)
				(curRoom newRoom: 99)
				(self dispose:)
			)
		)
	)
)

(instance sKillSteve of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(o_Riley setLoop: 6 setCel: 0 setCycle: CycleTo 1 1 self)
			)
			(1
				(sFX number: 52 loop: 1 flags: 1 play:)
				(o_Riley setCycle: CycleTo 1 1 self)
			)
			(2
				(sFX play:)
				(o_Riley setCycle: CycleTo 0 1 self)
			)
			(3
				(steve
					view: 722
					setLoop: 3
					setCel: 0
					cycleSpeed: 12
					setCycle: EndLoop self
				)
			)
			(4 (self dispose:))
		)
	)
)

(instance sKillCoalSteve of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(o_Riley
					setLoop: 7
					setCycle: Walk
					setMotion: MoveTo (- (steve x?) 40) (+ (steve y?) 20) self
				)
			)
			(1
				(o_Riley setLoop: 5 setCel: 0 setCycle: EndLoop self)
			)
			(2
				(o_Riley setLoop: 6 setCel: 0 setCycle: CycleTo 1 1 self)
			)
			(3
				(sFX number: 52 loop: 1 flags: 1 play:)
				(o_Riley setCycle: CycleTo 1 1 self)
			)
			(4
				(sFX play:)
				(o_Riley setCycle: CycleTo 0 1 self)
			)
			(5 (steve setCycle: Oscillate 1 self))
			(6 (self dispose:))
		)
	)
)

(instance sKillNailSteve of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(o_Riley
					setLoop: 7
					setCycle: Walk
					setMotion: MoveTo (- (steve x?) 50) (+ (steve y?) 20) self
				)
			)
			(1
				(o_Riley setLoop: 5 setCel: 0 setCycle: EndLoop self)
			)
			(2
				(o_Riley setLoop: 6 setCel: 0 setCycle: CycleTo 1 1 self)
			)
			(3
				(sFX number: 52 loop: 1 flags: 1 play:)
				(o_Riley setCycle: CycleTo 1 1 self)
			)
			(4
				(sFX play:)
				(o_Riley setCycle: CycleTo 0 1 self)
			)
			(5
				(steve setCel: (- (steve cel?) 1))
				(= ticks 20)
			)
			(6
				(steve setCel: (+ (steve cel?) 1))
				(= ticks 20)
			)
			(7 (self dispose:))
		)
	)
)

(instance sBurnBabyBurn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 721
					setLoop: 4
					setCel: 0
					setScale: Scaler 100 100 0 10
					cycleSpeed: 12
					setCycle: CycleTo 3 1 self
				)
			)
			(1
				(ego setCycle: EndLoop self)
				(furnaceDoor cycleSpeed: 12 setCycle: EndLoop)
			)
			(2
				(sFX number: 736 loop: 1 flags: 1 play:)
				(flames setCycle: EndLoop self)
			)
			(3
				(flames setCycle: BegLoop)
				(ego
					setLoop: 5
					setCel: 0
					cycleSpeed: 13
					setCycle: EndLoop self
				)
			)
			(4 (= ticks 120))
			(5
				(= deathReason 2)
				(curRoom newRoom: 99)
				(self dispose:)
			)
		)
	)
)

(instance sRemoveCoal of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 279 172 self)
			)
			(1
				(ego
					view: 721
					setLoop: 0
					setCel: 0
					setScale: Scaler 100 100 0 10
					setCycle: CycleTo 2 1 self
				)
			)
			(2
				(coalOnFace dispose:)
				(coalOnFaceFeat dispose:)
				(ego setCycle: EndLoop self)
			)
			(3
				(ego normalize: 831 loop: 0 setScale: 145)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sAwaken of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(smellHere dispose:)
				(ego setMotion: PolyPath 279 172 self)
			)
			(1
				(ego
					view: 721
					setLoop: 0
					setCel: 0
					setScale: Scaler 100 100 0 10
					setCycle: EndLoop self
				)
			)
			(2 (steve setCycle: Oscillate 1 self))
			(3 (= ticks 60))
			(4
				(ego
					normalize: 831
					loop: 0
					setScale: 145
					setMotion: PolyPath 237 165 self
				)
			)
			(5 (ego setHeading: 90 self))
			(6
				(steve
					setLoop: 2
					setCel: 0
					cycleSpeed: 12
					setCycle: EndLoop self
				)
			)
			(7
				(theGame handsOn:)
				(theIconBar disable: 0)
				(= seconds (if (HaveMouse) 6 else 12))
			)
			(8
				(curRoom setScript: sStepOnNail)
				(self dispose:)
			)
		)
	)
)

(instance sStepOnNail of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(steve
					view: 722
					setLoop: 1
					setCel: 0
					posn: 265 173
					setCycle: EndLoop self
				)
			)
			(1 (= ticks 45))
			(2
				(ego setHeading: 135)
				(steve
					setLoop: 2
					setCel: 0
					setCycle: EndLoop self
					setMotion: MoveTo 258 182 self
				)
				(sFX number: 722 flags: 1 play:)
			)
			(3)
			(4
				(sFX fade:)
				(Bset 65)
				((curRoom obstacles?)
					delete: ((curRoom obstacles?) at: 0)
				)
				((ScriptID 2720 0) doit: (curRoom obstacles?))
				(steve stopUpd:)
				(theIconBar enable: 0)
				(self dispose:)
			)
		)
	)
)

(instance sGetUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(steve setLoop: 3 setCel: 0 setCycle: EndLoop self)
			)
			(1 (= ticks 30))
			(2
				(steve
					view: 722
					setLoop: 0
					setCel: 0
					posn: 265 173
					setCycle: CycleTo 2 1 self
				)
			)
			(3
				(sFX number: 723 flags: 1 play:)
				(steve setCycle: EndLoop self)
			)
			(4
				(sFX stop:)
				(= local1 1)
				(steve setPri: -1 stopUpd:)
				(Bset 121)
				((curRoom obstacles?)
					delete: ((curRoom obstacles?) at: 0)
				)
				((ScriptID 2720 0) doit: (curRoom obstacles?))
				(messager say: 21 2 5)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sSteveHelp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(steve
					view: 724
					setPri: -1
					setLoop: 0
					setCel: 0
					cycleSpeed: 6
					moveSpeed: 6
					setCycle: Walk
					setScale: 173
					setMotion: MoveTo 230 162 self
				)
			)
			(1
				(steve
					setLoop: 1
					setCel: 0
					setMotion: MoveTo 233 154 self
				)
			)
			(2
				(steve
					setLoop: 2
					setCel: 0
					setMotion: MoveTo 270 153 self
				)
			)
			(3
				(sMoveStone cue:)
				(self dispose:)
			)
		)
	)
)

(instance sPushStone of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 240 154 self)
			)
			(1
				(ego
					view: 721
					setLoop: 7
					setCel: 0
					cycleSpeed: 12
					setScale: Scaler 100 100 0 10
					setCycle: CycleTo 4 1 self
				)
			)
			(2 (= ticks 60))
			(3 (ego setCycle: BegLoop self))
			(4
				(messager say: 5 4 4)
				(ego normalize: 831 setScale: 145 loop: 3)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sMoveStone of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 240 151 self)
				(steve setScript: sSteveHelp)
			)
			(1)
			(2
				(ego
					view: 721
					setScale: Scaler 100 100 0 10
					setLoop: 7
					setCel: 0
					cycleSpeed: 12
					setCycle: Oscillate 1
				)
				(steve
					view: 721
					setScale: Scaler 100 100 0 10
					setLoop: 6
					setCel: 0
					cycleSpeed: 12
					setCycle: Oscillate 1
				)
				(stone setMotion: MoveTo 176 (stone y?) self)
				(sFX number: 721 flags: 1 play:)
			)
			(3
				(sFX stop:)
				(stone stopUpd:)
				(ego setCycle: BegLoop self)
				(steve setCycle: BegLoop)
			)
			(4
				(ego normalize: 831 setScale: 145 loop: 3)
				(steve
					view: 812
					loop: 3
					setScale: 145
					setScale: 0
					cycleSpeed: 6
					stopUpd:
					setCycle: StopWalk -1
				)
				(walkHandler addToFront: tunnel)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sEnterTunnel of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 251 146 self)
			)
			(1 (ego setHeading: 0 self))
			(2
				(ego
					view: 721
					setLoop: 8
					setCel: 0
					setScale: Scaler 100 100 0 10
					setCycle: EndLoop self
				)
			)
			(3
				(ego
					setLoop: 9
					setCel: 0
					posn: 253 125
					setPri: 7
					cycleSpeed: 12
					setCycle: Forward
					setMotion: MoveTo 281 98 self
				)
			)
			(4
				(ego hide:)
				(steve setLoop: -1 setMotion: PolyPath 254 148 self)
			)
			(5
				(steve view: 721 setLoop: 10 setCel: 0 setCycle: EndLoop self)
			)
			(6
				(steve
					setLoop: 11
					setCel: 0
					posn: 254 129
					setPri: 7
					cycleSpeed: 9
					setCycle: Forward
					setMotion: MoveTo 285 94 self
				)
			)
			(7
				(steve hide:)
				(curRoom newRoom: (curRoom north?))
				(self dispose:)
			)
		)
	)
)

(instance sGetBoot of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 1)
			)
			(1
				(ego
					view: 721
					setScale: Scaler 100 100 190 90
					setLoop: 15
					cycleSpeed: 16
					setCycle: EndLoop self
				)
			)
			(2
				(boot dispose:)
				(ego get: 12)
				((ScriptID 21 0) doit: 781)
				(ego setCycle: BegLoop self)
			)
			(3
				(theGame points: 1 150)
				(= cycles 1)
			)
			(4
				(ego
					loop: 0
					setScale: Scaler 155 0 190 90
					normalize: 831
					setScale: 145
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance rileyTimer of Timer
	(properties)
)

(instance steve of Actor
	(properties
		x 261
		y 148
		noun 21
		view 721
		loop 1
		priority 12
		signal $4011
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(cond 
					((and (== loop 1) (== view 721)) (messager say: 21 1 1))
					((and (== loop 2) (== view 722)) (messager say: 21 1 2))
					(else (super doVerb: theVerb))
				)
			)
			(27
				(if local1
					(ego put: 16)
					((ScriptID 21 1) doit: 785)
					(messager say: 21 27)
				)
			)
			(23
				(if (and (== loop 2) (== view 721))
					(ego put: 12)
					((ScriptID 21 1) doit: 781)
					(curRoom setScript: sGetUp)
				else
					(super doVerb: theVerb)
				)
			)
			(8
				(if
					(or
						(== view 812)
						(and
							(== (steve view?) 722)
							(== (steve loop?) 0)
							(== (steve cel?) 7)
						)
					)
					(messager say: 21 8)
				else
					(super doVerb: theVerb)
				)
			)
			(4
				(if (and (== loop 1) (== view 721))
					(ego setMotion: PolyPath 279 172 self)
				else
					(super doVerb: theVerb)
				)
			)
			(6
				(if
					(or
						(== view 812)
						(and
							(== (steve view?) 722)
							(== (steve loop?) 0)
							(== (steve cel?) 7)
						)
					)
					(messager say: 21 6 7)
				else
					(super doVerb: theVerb)
				)
			)
			(2
				(if
					(or
						(== view 812)
						(and
							(== (steve view?) 722)
							(== (steve loop?) 0)
							(== (steve cel?) 7)
						)
					)
					(messager say: 21 2 8)
				else
					(super doVerb: theVerb)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (cue)
		(messager say: 21 4 1)
	)
)

(instance o_Riley of Actor
	(properties
		y 163
		view 722
		loop 5
		priority 15
		signal $4010
		name "o'Riley"
	)
)

(instance stone of Actor
	(properties
		x 224
		y 78
		noun 5
		view 723
		loop 1
		priority 8
		signal $0011
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(cond 
					((and local1 (== x 224)) (curRoom setScript: sMoveStone))
					((and local1 (!= x 224)) 0)
					(else (curRoom setScript: sPushStone))
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance furnaceDoor of Prop
	(properties
		x 141
		y 117
		noun 4
		approachX 189
		approachY 163
		view 723
		priority 11
		signal $0011
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: sBurnBabyBurn)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance flames of Prop
	(properties
		x 144
		y 101
		view 723
		loop 3
		priority 10
		signal $0011
	)
)

(instance coalOnFace of View
	(properties
		x 297
		y 135
		view 721
		loop 13
		priority 13
		signal $4011
	)
)

(instance boot of View
	(properties
		x 240
		y 153
		approachX 226
		approachY 153
		view 721
		loop 14
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(theGame points: 1 150)
				(curRoom setInset: inBoot)
			)
			(4
				(curRoom setScript: sGetBoot)
			)
			(8
				(theGame points: 1 150)
				(curRoom setInset: inBoot)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance coalOnFaceFeat of Feature
	(properties
		y 155
		nsTop 129
		nsLeft 290
		nsBottom 139
		nsRight 301
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (messager say: 21 1 1))
			(4
				(curRoom setScript: sRemoveCoal)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance smellHere of Feature
	(properties
		y 154
		nsTop 129
		nsLeft 290
		nsBottom 139
		nsRight 301
		approachX 297
		approachY 134
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (messager say: 21 1 1))
			(4
				(ego setMotion: PolyPath 279 172 steve)
			)
			(24
				(curRoom setScript: sAwaken)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance stairs of Feature
	(properties
		y 1
		noun 9
	)
)

(instance junk1 of Feature
	(properties
		y 1
		noun 10
	)
)

(instance junk2 of Feature
	(properties
		y 1
		noun 11
	)
)

(instance coal of Feature
	(properties
		y 1
		noun 12
	)
)

(instance shovel1 of Feature
	(properties
		x 300
		y 1
		noun 13
	)
)

(instance coalShute of Feature
	(properties
		y 1
		noun 14
	)
)

(instance longPipe of Feature
	(properties
		y 1
		noun 15
	)
)

(instance bigPipes of Feature
	(properties
		y 1
		noun 16
	)
)

(instance drain of Feature
	(properties
		y 1
		noun 17
	)
)

(instance shovel2 of Feature
	(properties
		y 1
		noun 18
	)
)

(instance tunnel of Feature
	(properties
		y 1
		noun 19
		approachX 255
		approachY 135
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(curRoom setScript: sEnterTunnel)
			)
			(4
				(curRoom setScript: sEnterTunnel)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance pipe1 of Feature
	(properties
		y 1
		noun 1
	)
)

(instance pipe2 of Feature
	(properties
		y 1
		noun 2
	)
)

(instance furnace of Feature
	(properties
		y 1
		noun 3
	)
)

(instance light1 of Feature
	(properties
		x 64
		y 73
		noun 7
		nsTop 67
		nsLeft 59
		nsBottom 80
		nsRight 70
		sightAngle 40
	)
)

(instance light2 of Feature
	(properties
		x 232
		y 68
		noun 8
		nsTop 62
		nsLeft 225
		nsBottom 75
		nsRight 239
		sightAngle 40
	)
)

(instance furnaceGrate of Feature
	(properties
		x 158
		y 136
		noun 6
		nsTop 128
		nsLeft 141
		nsBottom 145
		nsRight 176
		sightAngle 40
	)
)

(instance maintDoor of Feature
	(properties
		y 1
		noun 20
		nsTop 86
		nsLeft 7
		nsBottom 139
		nsRight 35
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (!= msgType 2) (sFXLocked play:))
				(messager say: 20 4)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance inBoot of Inset
	(properties
		view 721
		loop 14
		cel 1
		x 250
		y 120
		priority 15
		disposeNotOnMe 1
		modNum 15
		noun 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: sGetBoot)
				(inBoot dispose:)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance sFX of Sound
	(properties
		flags $0001
	)
)

(instance sFXLocked of Sound
	(properties
		flags $0001
		number 48
	)
)
