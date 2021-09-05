;;; Sierra Script 1.0 - (do not remove this comment)
(script# 440)
(include game.sh) (include "440.shm")
(use Main)
(use TellerIcon)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm440 0
)

(local
	local0 =  150
	local1 =  130
	local2
	local3
	talkCount
	local5
	local6
	local7
	local8 = [0 -16 17 97 -43 96 50 94 78 93 81 999]
	[local20 2]
	local22 = [0 1 -2 4 -5 999]
	local28 = [0 3 999]
	local31 = [0 -6 98 999]
	local35 = [0 -7 8 3 999]
	local40 = [0 9 999]
	local43 = [0 -2 -5 -6 -7 999]
	[local49 7]
	local82 = [0 11 18 19 20 999]
	[local86 2]
	local89 = [0 1 10 -4 11 12 999]
	local92 = [0 -26 -28 -31 33 34 -24 999]
	local98 = [0 27 999]
	local106 = [0 29 30 999]
	local110 = [0 32 999]
	local113 = [0 25 999]
	local119 = [0 -26 -28 -31 -24 999]
	local124 = [0 -35 99 37 38 39 40 999]
	local127 = [0 42 41 999]
	local135 = [0 -35 999]
	local139 = [0 100 49 3 -45 999]
	local143 = [0 46 48 47 999]
	local147 = [0 -45 999]
	local152 = [0 -50 58 57 59 60 61 999]
	local155 = [0 56 -53 999]
	local159 = [0 54 55 999]
	local163 = [0 -50 -53 999]
	local169 = [0 -63 -65 68 999]
	local173 = [0 64 999]
	local178 = [0 66 67 999]
	local182 = [0 -63 -65 999]
	local188 = [0 76 -72 70 71 999]
	local193 = [0 73 -74 999]
	local199 = [0 -75 2 79 999]
	local178_2 = [0 -72 -74 999]
	local182_2 = [0 80 81 82 83 999]
	local188_2 = [0 85 86 87 999]
	local193_2 = [0 89 92 91 90 999]
	[local199_2 6]
)
(procedure (localproc_15d0 param1)
	(return (* 2 (/ (+ (* param1 800) (- local7 1)) local7)))
)

(instance rm440 of Room
	(properties
		noun N_ROOM
		picture 440
	)
	
	(method (init)
		(HandsOff)
		(walkHandler addToFront: curRoom)
		(if (== prevRoomNum 450)
			(curRoom
				addObstacle:
					((Polygon new:)
						type: PContainedAccess
						init:
							37 189
							129 188
							128 129
							196 129
							227 146
							246 155
							279 155
							279 111
							231 119
							215 118
							195 106
							152 106
							110 123
							37 123
						yourself:
					)
			)
		else
			(curRoom
				addObstacle:
					((Polygon new:)
						type: PContainedAccess
						init:
							37 189
							126 189
							126 128
							191 128
							219 140
							227 156
							279 156
							279 112
							244 119
							227 121
							186 109
							159 105
							131 105
							113 87
							37 102
						yourself:
					)
			)
		)
		(fire setCycle: Forward init:)
		(firePlace init:)
		(shield init:)
		(pot init:)
		(rightBench init:)
		(wallSkin init:)
		(mat approachVerbs: V_DO init:)
		(cSound setLoop: -1 changeTo: 440)
		(soundFx number: 913 setLoop: -1 play: 127)
		(super init:)
		(= [local20 0] @local8)
		(egoActions init: ego @local8 @local20)
		(cond 
			(
				(and
					(Btst 74)
					(< global402 10)
					(!= Day dayStoleMagicDrum)
				)
				(curRoom setScript: eventTen)
			)
			((and (Btst 65) (or (< global402 9) (== global402 10)))
				(curRoom setScript: eventNine)
			)
			((and (Btst 16) (< global402 8))
				(curRoom setScript: eventEight)
			)
			((and (Btst 38) (< global402 7))
				(curRoom setScript: eventSeven)
			)
			((== global402 5)
				(curRoom setScript: eventSix)
			)
			((and (Btst 29) (< global402 5))
				(curRoom setScript: eventFive)
			)
			((== prevRoomNum 450)
				(curRoom setScript: eventOne)
			)
			((== global402 1)
				(curRoom setScript: eventTwo)
			)
			((== global402 2)
				(curRoom setScript: eventThree)
			)
			((== global402 3)
				(curRoom setScript: eventFour)
			)
			(else
				(ego
					normalize:
					x: 110
					y: 150
					actions: egoActions
					setScale:
					scaleX: 156
					scaleY: 156
					noun: 4
					init:
				)
				(= [local199_2 0] @local92)
				(= [local199_2 1] @local98)
				(= [local199_2 2] @local106)
				(= [local199_2 3] @local110)
				(= [local199_2 4] @local113)
				(uhuraTell
					init: (ScriptID 34 1) @local92 @local199_2 @local119
				)
				((ScriptID 34 1)
					view: 433
					loop: 2
					cel: 0
					x: 192
					y: 92
					setScale:
					scaleX: 156
					scaleY: 156
					noun: 2
					init:
				)
				(uhuraHead setScale: scaleX: 160 scaleY: 160 init:)
				(simba
					setScale:
					scaleX: 156
					scaleY: 156
					x: 220
					y: 94
					loop: 5
					cel: 1
					noun: 5
					init:
				)
				(HandsOn)
			)
		)
	)
	
	(method (doit)
		(cond 
			((and (GameIsRestarting) (== (ego view?) 40))
				(= local2 1)
			)
			((self script?) 0)
			((and (ego mover?) (== (ego view?) 40))
				(if (IsObject (ego looper?)) ((ego looper?) dispose:))
				(ego setMotion: 0 setScript: egoGetUp)
			)
			((and (< (ego x?) 140) (> (ego y?) 155))
				(if (== prevRoomNum 450)
					(curRoom setScript: egoLeaves)
				else
					(curRoom setScript: exitRoom)
				)
			)
		)
		(cond 
			((uhuraHead cycler?) 0)
			((and (== (Random 1 200) 50) (cast contains: uhuraHead))
				(if (== (uhuraHead cel?) 0)
					(uhuraHead setCycle: EndLoop)
				else
					(uhuraHead setCycle: BegLoop)
				)
			)
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(walkHandler delete: curRoom)
		(if (uhuraHead cycler?) ((uhuraHead cycler?) dispose:))
		(soundFx stop:)
		(UnLoad RES_VIEW 425)
		(UnLoad RES_VIEW 433)
		(LoadMany FALSE 34 35 949)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_SLEEP
				(self setScript: goToBed)
			)
			(V_WALK
				(egoActions doVerb: V_WALK)
			)
			(V_DO
				(egoActions doVerb: V_DO)
			)
			(else 
				(super doVerb: theVerb)
			)
		)
	)
)

(instance rakeeshTell of Teller
	(method (doVerb theVerb)
		(switch theVerb
			(V_TALK
				(= local3 1)
				(++ talkCount)
				(super doVerb: theVerb &rest)
			)
			(V_LOOK
				(= local3 1)
				(super doVerb: theVerb &rest)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance uhuraTell of Teller
	(method (showDialog)
		(super
			showDialog:
				-72 (< global453 3)
				75 (< global453 3)
				79 (== origHeroType FIGHTER)
		)
	)
	
	(method (doChild)
		(if (or (== query -74) (== query -101))
			(ego solvePuzzle: 268 2)
		)
		(if (== query -75)
			(ego solvePuzzle: 268 2)
		)
		(if (== query -4)
			(ego solvePuzzle: 267 2)
			(return TRUE)
		)
		(return
			(cond 
				((and (== query -74) (== heroType FIGHTER))
					(super doChild: query)
					(= query -101)
				)
				((< query 0)
					(super doChild: query)
				)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_TALK
				(= local3 1)
				(++ talkCount)
				(super doVerb: theVerb &rest)
			)
			(V_LOOK
				(= local3 1)
				(super doVerb: theVerb &rest)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance egoActions of Teller
	(method (showDialog)
		(super
			showDialog:
				-16 (== global402 1)
				17 (== global402 1)
				97 (!= global402 1)
				-43 (!= global402 1)
				96
				(if (or (== global402 2) (== global402 3) (== global402 4))
				else
					(== global402 6)
				)
				50 (== global402 5)
				94 (== global402 7)
				78 (== global402 8)
				93 (== global402 9)
				81 (== global402 10)
		)
	)
	
	(method (doChild)
		(return
			(switch query
				(-16
					(messager say: 4 5 16)
					(curRoom setScript: egoLeaves)
					(return 0)
				)
				(-43
					(curRoom setScript: goodNightEgo)
					(return 0)
				)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_WALK
				(cond 
					((curRoom script?) 0)
					((== (ego view?) 40)
						(HandsOff)
						(= local0 ((User curEvent?) x?))
						(= local1 ((User curEvent?) y?))
						(curRoom setScript: egoGetUp)
					)
				)
			)
			(V_TALK
				(if (== prevRoomNum 450)
					(= local3 1)
				)
				(super doVerb: V_TALK)
			)
			(V_DO
				(cond 
					((== prevRoomNum 450) 0)
					((curRoom script?) 0)
					((== local2 1) 0)
					(else (curRoom setScript: sitDown))
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance simbaTeller of Teller)

(instance goodNightEgo of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: 4 5 43 0 self)
			)
			(1
				(if local2
					(= local0 120)
					(= local1 180)
					(HandsOff)
					(self setScript: egoGetUp self)
				else
					(ego setMotion: PolyPath 120 180 self)
				)
			)
			(2 (curRoom newRoom: 420))
		)
	)
)

(instance goToBed of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 15 6 102 0 self)
			)
			(1 (self dispose:))
		)
	)
)

(instance eventTen of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(simba
					setScale:
					scaleX: 156
					scaleY: 156
					x: 220
					y: 94
					loop: 5
					cel: 1
					noun: 5
					init:
				)
				(= global402 10)
				(= [local199_2 0] @local193_2)
				(uhuraTell init: (ScriptID 34 1) @local193_2 @local199_2)
				(self setScript: enterRoom self)
			)
			(1
				(messager say: 2 6 88 0 self)
			)
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance eventNine of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= [local86 0] @local82)
				(simbaTeller init: simba @local82 @local86)
				(simba setScale: scaleX: 156 scaleY: 156 noun: 5 init:)
				(= global402 9)
				(= [local199_2 0] @local188_2)
				(uhuraTell init: (ScriptID 34 1) @local188_2 @local199_2)
				(self setScript: enterRoom self)
			)
			(1
				(messager say: 2 6 84 0 self)
			)
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance eventEight of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= [local86 0] @local82)
				(simbaTeller init: simba @local82 @local86)
				(simba
					setScale:
					scaleX: 156
					scaleY: 156
					x: 142
					y: 105
					noun: 5
					init:
				)
				(= global402 8)
				(= [local199_2 0] @local182_2)
				(uhuraTell init: (ScriptID 34 1) @local182_2 @local199_2)
				(self setScript: enterRoom self)
			)
			(1
				(messager say: 2 6 77 0 self)
			)
			(2
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance eventSeven of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(simba
					setScale:
					scaleX: 156
					scaleY: 156
					x: 210
					y: 94
					loop: 5
					cel: 1
					noun: 5
					init:
				)
				(= global402 7)
				(= [local199_2 0] @local188)
				(= [local199_2 1] @local193)
				(= [local199_2 2] @local199)
				(uhuraTell
					init: (ScriptID 34 1) @local188 @local199_2 @local178_2
				)
				(self setScript: enterRoom self)
			)
			(1
				(messager say: 2 6 69 0 self)
			)
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance eventSix of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(simba
					setScale:
					scaleX: 156
					scaleY: 156
					x: 210
					y: 94
					loop: 5
					cel: 1
					noun: 5
					init:
				)
				(= [local199_2 0] @local169)
				(= [local199_2 1] @local173)
				(= [local199_2 2] @local178)
				(uhuraTell
					init: (ScriptID 34 1) @local169 @local199_2 @local182
				)
				(++ global402)
				(self setScript: enterRoom self)
			)
			(1
				(messager say: 2 6 62 0 self)
			)
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance eventFive of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= [local86 0] @local82)
				(simbaTeller init: simba @local82 @local86)
				(simba setScale: scaleX: 156 scaleY: 156 noun: 5 init:)
				(= global402 5)
				(= [local199_2 0] @local152)
				(= [local199_2 1] @local155)
				(= [local199_2 2] @local159)
				(uhuraTell
					init: (ScriptID 34 1) @local152 @local199_2 @local163
				)
				(self setScript: enterRoom self)
			)
			(1
				(messager say: 2 6 52 0 self)
			)
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance eventFour of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(simba
					setScale:
					scaleX: 156
					scaleY: 156
					x: 210
					y: 94
					loop: 5
					cel: 1
					noun: 5
					init:
				)
				(= [local199_2 0] @local139)
				(= [local199_2 1] @local143)
				(uhuraTell
					init: (ScriptID 34 1) @local139 @local199_2 @local147
				)
				(self setScript: enterRoom self)
			)
			(1
				(messager say: 2 6 44 0 self)
			)
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance eventThree of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= [local86 0] @local82)
				(simbaTeller init: simba @local82 @local86)
				(simba setScale: scaleX: 156 scaleY: 156 noun: 5 init:)
				(++ global402)
				(= [local199_2 0] @local124)
				(= [local199_2 1] @local127)
				(uhuraTell
					init: (ScriptID 34 1) @local124 @local199_2 @local135
				)
				(self setScript: enterRoom self)
			)
			(1
				(messager say: 2 6 36 0 self)
			)
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance eventTwo of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(simba
					setScale:
					scaleX: 156
					scaleY: 156
					x: 210
					y: 94
					loop: 5
					cel: 1
					noun: 5
					init:
				)
				(= [local199_2 0] @local92)
				(= [local199_2 1] @local98)
				(= [local199_2 2] @local106)
				(= [local199_2 3] @local110)
				(= [local199_2 4] @local113)
				(uhuraTell
					init: (ScriptID 34 1) @local92 @local199_2 @local119
				)
				(++ global402)
				(self setScript: enterRoom self)
			)
			(1
				(messager say: 2 6 23 0 self)
			)
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance exitRoom of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath (ego x?) (+ (ego y?) 40) self)
			)
			(1 (curRoom newRoom: 420))
		)
	)
)

(instance egoLeaves of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Face ego (ScriptID 34 1) self)
			)
			(1
				(messager say: 2 6 21 0 self)
			)
			(2
				(if (uhuraHead cycler?) ((uhuraHead cycler?) dispose:))
				(cast eachElementDo: #dispose)
				(curRoom drawPic: 0 9)
				(= cycles 3)
			)
			(3
				(messager say: 6 6 14 0 self)
			)
			(4 (curRoom newRoom: 430))
		)
	)
)

(instance uhuraSpeaks of Script
	(method (doit)
		(if (and (== state 1) (not (IsObject fastCast)))
			(= cycles 1)
		)
		(super doit: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Face ego (ScriptID 34 1) self)
			)
			(1 0)
			(2 (= cycles 18))
			(3
				(messager say: 2 6 22 0 self)
			)
			(4
				(if (uhuraHead cycler?) ((uhuraHead cycler?) dispose:))
				(cast eachElementDo: #dispose)
				(curRoom drawPic: 0 9)
				(= cycles 6)
			)
			(5
				(messager say: 6 6 14 0 self)
			)
			(6 (curRoom newRoom: 430))
		)
	)
)

(instance enterRoom of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					normalize:
					x: 110
					y: 180
					actions: egoActions
					setScale:
					scaleX: 156
					scaleY: 156
					setMotion: PolyPath 110 150 self
					noun: 4
					init:
				)
				((ScriptID 34 1)
					view: 433
					loop: 2
					cel: 0
					x: 192
					y: 92
					setScale:
					scaleX: 156
					scaleY: 156
					noun: 2
					init:
				)
				(uhuraHead setScale: scaleX: 160 scaleY: 160 init:)
			)
			(1 (self dispose:))
		)
	)
)

(instance eventOne of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= [local49 0] @local22)
				(= [local49 1] @local28)
				(= [local49 2] @local31)
				(= [local49 3] @local35)
				(= [local49 4] @local40)
				(rakeeshTell
					init: (ScriptID 35 1) @local22 @local49 @local43
				)
				((ScriptID 34 0)
					x: 196
					y: 10
					textX: -175
					textY: 3
					talkWidth: 140
				)
				(if (ego looper?)
					((ego looper?) dispose:)
					(ego looper: 0)
				)
				(ego
					view: 40
					setLoop: 1
					x: 240
					y: 135
					setCycle: 0
					setCel: 255
					actions: egoActions
					setScale:
					scaleX: 156
					scaleY: 156
					init:
					noun: 4
				)
				(= local2 1)
				((ScriptID 35 1)
					view: 432
					setScale:
					scaleX: 128
					scaleY: 128
					loop: 3
					cel: 0
					x: 87
					y: 105
					setPri: 4
					noun: 1
					ignoreActors: 1
					signal: (| ((ScriptID 35 1) signal?) $1000)
					init:
					stopUpd:
				)
				(= [local86 0] @local82)
				(simbaTeller init: simba @local82 @local86)
				(simba
					setScale:
					scaleX: 156
					scaleY: 156
					x: 109
					y: 109
					setStep: 2 1
					noun: 5
					init:
				)
				(= [local199_2 0] @local89)
				(uhuraTell init: (ScriptID 34 1) @local89 @local199_2)
				((ScriptID 34 1)
					view: 433
					loop: 2
					cel: 0
					x: 192
					y: 92
					setScale:
					scaleX: 156
					scaleY: 156
					noun: 2
					init:
				)
				(uhuraHead setScale: scaleX: 160 scaleY: 160 init:)
				(self setScript: checkTime self)
			)
			(1
				(ego setScript: timeEgo)
				(++ global402)
				(messager say: 1 6 1 0 self)
			)
			(2 (HandsOn) (self dispose:))
		)
	)
)

(instance checkTime of Script
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(= local6 (GetTime))
				(= cycles 1)
			)
			(1 (= cycles 5))
			(2
				(= local7 (- (= temp0 (GetTime)) local6))
				(= ticks 1)
			)
			(3 (self dispose:))
		)
	)
)

(instance sitDown of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (ego looper?)
					((ego looper?) dispose:)
					(ego looper: 0)
				)
				(ego
					view: 40
					setLoop: (if (>= (ego x?) ((ScriptID 34 1) x?)) 1 else 0)
					setCycle: EndLoop self
				)
				(= local3 (= local2 1))
			)
			(1 (HandsOn) (self dispose:))
		)
	)
)

(instance egoGetUp of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= local3 1)
				(ego setCel: (ego lastCel:) setCycle: BegLoop self)
			)
			(1
				(= local2 0)
				(HandsOn)
				(ego view: 0 normalize: setMotion: PolyPath local0 local1)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance timeEgo of Script
	(method (doit)
		(if (or (ego mover?) local3)
			(if (>= talkCount 10)
				(= local3 0)
				(= cycles 1)
			else
				(= local3 0)
				(= cycles (localproc_15d0 10))
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles (localproc_15d0 10))
			)
			(1
				(curRoom setScript: uhuraSpeaks)
			)
		)
	)
)

(instance firePlace of Feature
	(properties
		x 172
		y 140
		noun 9
		nsTop 132
		nsLeft 155
		nsBottom 149
		nsRight 189
		sightAngle 180
	)
)

(instance shield of Feature
	(properties
		x 235
		y 46
		noun 10
		nsTop 25
		nsLeft 223
		nsBottom 68
		nsRight 248
		sightAngle 180
	)
)

(instance pot of Feature
	(properties
		x 252
		y 147
		noun 11
		nsTop 136
		nsLeft 234
		nsBottom 159
		nsRight 270
		sightAngle 180
	)
)

(instance leftBench of Feature
	(properties
		x 85
		y 96
		nsTop 81
		nsLeft 31
		nsBottom 112
		nsRight 140
		sightAngle 180
	)
)

(instance rightBench of Feature
	(properties
		x 227
		y 90
		noun 13
		nsTop 81
		nsLeft 172
		nsBottom 114
		nsRight 283
		sightAngle 180
	)
)

(instance wallSkin of Feature
	(properties
		x 79
		y 51
		noun 14
		nsTop 25
		nsLeft 32
		nsBottom 77
		nsRight 127
		sightAngle 180
	)
)

(instance mat of Feature
	(properties
		x 87
		y 95
		noun 12
		sightAngle 40
		onMeCheck cGREEN
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(cond 
					((ego script?) 0)
					((== global402 1) 0)
					((== (ego view?) 40) 0)
					(else (ego setScript: sitDown))
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance uhuraHead of Prop
	(properties
		x 192
		y 64
		noun 2
		view 433
		signal ignrAct
		detailLevel 3
	)
)

(instance fire of Prop
	(properties
		x 171
		y 141
		noun 3
		view 423
		priority 15
		signal (| ignrAct fixPriOn)
	)
)

(instance simba of Actor
	(properties
		x 189
		y 111
		noun 5
		view 425
		loop 4
		cel 3
		signal ignrHrz
		detailLevel 3
	)
)
