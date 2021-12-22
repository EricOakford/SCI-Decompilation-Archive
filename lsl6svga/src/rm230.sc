;;; Sierra Script 1.0 - (do not remove this comment)
(script# 230)
(include sci.sh)
(use Main)
(use fileScr)
(use EgoDead)
(use OccCyc)
(use LarryRoom)
(use PolyPath)
(use Polygon)
(use Feature)
(use StopWalk)
(use DPath)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm230 0
)

(local
	local0
	local1
	local2
	local3
	local4
	[local5 11] = [224 198 169 211 248 82 80 72 99 90]
	local16
	local17
	local18 =  1
	[local19 3]
	pEventMessage
)
(instance rm230 of LarryRoom
	(properties
		noun 1
		picture 230
		horizon 0
	)
	
	(method (init)
		(curRoom
			addObstacle:
				(= local2
					((Polygon new:)
						type: 2
						init: 41 66 30 63 31 53 58 46 72 56
						yourself:
					)
				)
				(= local3
					((Polygon new:)
						type: 2
						init: 93 40 126 36 148 40 138 50 116 50 94 54 82 55
						yourself:
					)
				)
				(= local4
					((Polygon new:)
						type: 2
						init:
							219
							40
							243
							43
							260
							45
							275
							48
							297
							55
							287
							66
							274
							65
							269
							60
							258
							58
							255
							53
							247
							56
							239
							54
							229
							49
							219
							51
							205
							49
						yourself:
					)
				)
				((Polygon new:)
					type: 3
					init:
						25
						60
						30
						70
						22
						88
						21
						112
						39
						127
						160
						138
						264
						129
						292
						118
						292
						90
						294
						69
						306
						60
						319
						60
						319
						47
						296
						42
						282
						46
						249
						41
						178
						32
						161
						32
						148
						39
						129
						35
						47
						44
						24
						47
						0
						47
						0
						60
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 140 49 151 38 167 34 189 36 201 49
					yourself:
				)
				(= local0
					((Polygon new:)
						type: 2
						init:
							31
							81
							62
							63
							122
							51
							202
							51
							244
							58
							274
							70
							289
							87
							289
							110
							254
							123
							205
							118
							171
							110
							140
							110
							105
							123
							47
							118
							25
							101
						yourself:
					)
				)
		)
		(if (not (Btst 72))
			(curRoom
				addObstacle:
					(= local1
						((Polygon new:)
							type: 3
							init:
								103
								112
								157
								99
								168
								99
								222
								111
								264
								110
								275
								100
								267
								89
								245
								76
								215
								66
								154
								65
								110
								68
								84
								74
								63
								81
								45
								95
								46
								107
							yourself:
						)
					)
			)
		else
			(curRoom
				addObstacle:
					(= local1
						((Polygon new:)
							type: 3
							init:
								103
								112
								157
								99
								168
								99
								222
								111
								264
								110
								275
								100
								246
								102
								198
								86
								188
								62
								154
								65
								110
								68
								84
								74
								63
								81
								45
								95
								46
								107
							yourself:
						)
					)
			)
		)
		(if (== prevRoomNum 250) (= style 9))
		(super init: &rest)
		(Bset 9)
		(theMusic2 stop:)
		(cond 
			((Btst 8)
				(theMusic
					number: 337
					play:
					setVol: 127
					setLoop: -1
					mute: 1 4
					mute: 1 5
					mute: 1 6
					mute: 1 7
					mute: 1 8
					mute: 1 9
				)
			)
			((== prevRoomNum 240))
			(else
				(theMusic
					number: 230
					play:
					setVol: 50
					setLoop: -1
					fade: 127 10 10 0
				)
			)
		)
		(if (== (ego view?) 239)
			(ego setCycle: Fwd xStep: 2 yStep: 2 moveSpeed: 6)
			(Bset 27)
			(if (Btst 72)
				(poolBar init:)
			else
				(poolBar init: baseSetter: poolBase)
				(poolBase doit:)
			)
		)
		(if (== (ego view?) 900)
			(ego
				normalize: 232
				ignoreActors: 0
				init:
				approachX: 162
				approachY: 133
				approachVerbs: 47 45
				actions: egoActions
			)
		else
			(ego
				normalize:
				init:
				ignoreActors: 0
				actions: egoActions
				approachX: 162
				approachY: 133
				approachVerbs: 47 45
			)
		)
		(curRoom
			setScript:
				(switch prevRoomNum
					(505 enterFromHall)
					(860 enterFromBeach)
					(820 enterFromWoods)
					(270 takeADive)
					(240 enterFromBar)
					(250 doTowerScene)
					(else  enterFromAerobics)
				)
		)
		(chairFtr init:)
		(bushes init:)
		(poolFtr init: approachVerbs: 35 4 49)
		(aerobicsDoor
			init:
			setPri: 25
			setCel: (if (== prevRoomNum 440) 6 else 0)
			approachVerbs: 4
			ignoreActors: 0
		)
		(if (and (not (ego has: 2)) (not (Btst 174)))
			(beaver init: approachVerbs: 4 5 ignoreActors: 1)
			(beavFeat
				init:
				approachVerbs: 4 5
				approachX: 205
				approachY: 45
			)
		)
		(window1 init: approachVerbs: 1 4 2 6)
		(window2 init: approachVerbs: 1 4 2 6)
		(window3 init: approachVerbs: 1 4 2 6)
		(window4 init: approachVerbs: 1 4 2 6)
		(leftDoor init: approachVerbs: 1 4 3 6)
		(walkHandler addToFront: leftDoor)
		(if (not (Btst 8))
			(lifeGuard init: setPri: 61 approachVerbs: 2 4 6 50)
			(poolBar
				init:
				setLoop: 2 1
				setCycle: OccCyc self 1 5 60
				setStep: 1 1
				moveSpeed: 12
				cycleSpeed: 12
				ignoreActors: 0
				approachVerbs: 1 4
			)
			(if (and (Btst 72) (not (Btst 174)))
				(poolBar posn: 236 86 ignoreActors: 1)
				(merr
					x: (+ (poolBar x?) 19)
					y: (+ (poolBar y?) 10)
					ignoreActors: 1
					init:
				)
				(kenny
					x: (- (poolBar x?) 32)
					y: (- (poolBar y?) 6)
					ignoreActors: 1
					init:
				)
				(barLady
					x: (+ (poolBar x?) 2)
					y: (+ (poolBar y?) 1)
					cel: 3
					ignoreActors: 0
					init:
				)
			)
			(if (not (Btst 8))
				(bather1 init: approachVerbs: 1 4 6 2)
			)
			(if (not (Btst 8))
				(bather2 init: approachVerbs: 1 4 6 2)
			)
			(if (== (Random 1 4) 4)
				(bather4
					init:
					setPri: 50
					ignoreActors: 1
					approachVerbs: 1 4 6 2
				)
				(if
					(and
						(not (ego has: 20))
						(not (cast contains: merr))
						(Random 0 1)
					)
					(bather4 ignoreActors: 1 setPri: -1 setScript: girlDiver)
				)
			)
			(if (== (Random 1 4) 4)
				(bather3
					init:
					setPri: 61
					ignoreActors: 1
					approachVerbs: 1 4 6 2
				)
				(if
					(and
						(not (ego has: 20))
						(not (cast contains: bather4))
						(not (cast contains: merr))
						(Random 0 1)
					)
					(bather3 ignoreActors: 1 setPri: 61 setScript: guyDiver)
				)
			)
		)
		(towerDoor
			approachVerbs: 4 50 52
			init:
			ignoreActors: 1
			setPri: 38
		)
		(poolEdge init: approachVerbs: 4 49)
		(barWindow init:)
		(if (== prevRoomNum 240) (poolBar ignoreActors: 0))
	)
	
	(method (doit &tmp egoEdgeHit)
		(super doit: &rest)
		(= egoEdgeHit (ego edgeHit?))
		(cond 
			(script 0)
			(
			(and (not (Btst 4)) (== (Random 0 5000) 500))
				(if (Random 0 1)
					(bungeeJumper
						init:
						setPri: 199
						setCycle: End bungeeJumper
					)
					(sFx number: 235 loop: 1 play:)
				else
					(bungeeJumper
						loop: 1
						init:
						setPri: 199
						setCycle: End bungeeJumper
					)
					(sFx number: 235 loop: 1 play:)
				)
			)
			((== egoEdgeHit 4)
				(cond 
					((ego has: 20) (self setScript: gimmeMyKeyDammit))
					((== (ego view?) 236) (self setScript: getDecent 0 0))
					(else (self setScript: exitWest))
				)
			)
			((== egoEdgeHit 2)
				(cond 
					((ego has: 20) (self setScript: gimmeMyKeyDammit))
					((== (ego view?) 236) (self setScript: getDecent 0 2))
					(else (self setScript: exitEast))
				)
			)
			(
				(and
					(OneOf (ego view?) 2291 229 239)
					(== (ego cel?) 2)
					(not (sFx handle?))
				)
				(sFx number: 233 loop: 1 play:)
			)
		)
	)
	
	(method (dispose)
		(walkHandler delete: leftDoor)
		(if (== (ego view?) 232) (ego view: 900))
		(ego approachVerbs: 0)
		(DisposeScript -572)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(4
					(if (Btst 27)
						(self setScript: getOffBeaver)
					else
						(super doVerb: theVerb &rest)
					)
				)
				(49
					(if (== (ego view?) 239)
						(messager say: 1 49 15)
					else
						(super doVerb: theVerb)
					)
					(return 1)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance handOnPoolBar of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(proc79_3 ego poolBar self)
			)
			(1 (= cycles 2))
			(2 (curRoom newRoom: 240))
		)
	)
)

(instance enterFromBar of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (Btst 27)
					(ego x: 214 y: 98 cycleSpeed: 12 moveSpeed: 6)
					(local0 type: 3)
					(local1 type: 3)
				)
				(= cycles 2)
			)
			(1
				(if
				(and (not (Btst 109)) (Btst 55) (not (Btst 8)))
					(messager say: 0 0 22)
					(Bset 109)
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance doTowerScene of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bclr 27)
				(Bclr 74)
				(UpdateScreenItem ((ScriptID 92 3) view: 1900))
				(PalVary 8 230)
				(PalVary 0 230 0 64 1)
				(ego posn: 186 50 normalize: 232 1)
				(barLady
					view: 2321
					loop: 2
					cel: 0
					x: 171
					y: 52
					ignoreActors: 0
					init:
				)
				(= cycles 2)
			)
			(1
				(curRoom showControls: 1)
				(theGame controlsVisible: 0 drawControls:)
				(= cycles 2)
			)
			(2 (= seconds 3))
			(3
				(messager say: 1 0 14 0 self)
			)
			(4
				(barLady cycleSpeed: 12 setCycle: End self)
				(towerDoor setCycle: End)
			)
			(5
				(sFx number: 32 loop: 1 play:)
				(barLady
					setLoop: 0 1
					cycleSpeed: 6
					setCycle: Walk
					setMotion: MoveTo 169 47 self
				)
			)
			(6
				(barLady
					setLoop: 3 1
					setPri: 37
					setCycle: Fwd
					moveSpeed: 10
					cycleSpeed: 10
					setMotion: MoveTo 169 1 self
				)
			)
			(7
				(barLady dispose:)
				(ego setMotion: MoveTo 169 50 self)
			)
			(8
				(ego
					view: 238
					setLoop: 6 1
					setCycle: Walk
					setMotion: MoveTo 169 46 self
				)
			)
			(9
				(ego setPri: 37)
				(towerDoor setCycle: Beg self)
			)
			(10
				(sFx number: 33 loop: 1 play:)
				(ego
					moveSpeed: 8
					cycleSpeed: 10
					setMotion: MoveTo 169 1 self
				)
			)
			(11
				(PalVary 3)
				(curRoom newRoom: 260)
			)
		)
	)
)

(instance enterHall of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo 33 43 self)
			)
			(1
				(if (Btst 168) (Bset 72))
				(theMusic fade:)
				(curRoom newRoom: 505)
			)
		)
	)
)

(instance gimmeMyKeyDammit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 21 0 6 0 self)
			)
			(1
				(ego setMotion: PolyPath 35 65 self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance getDecent of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 21 0 5 0 self)
			)
			(1
				(switch register
					(0
						(ego setMotion: PolyPath 35 65 self)
					)
					(1 (self cue:))
					(2
						(ego setMotion: PolyPath (- (ego x?) 5) (ego y?) self)
					)
				)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance getTowerKey of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 30 66 self)
			)
			(1
				(cond 
					((and (Btst 166) (not (Btst 90))) (messager say: 16 2 21 0 self))
					((Btst 166) (messager say: 16 2 17 0 self))
					((not local16) (= local16 1) (messager sayRange: 16 2 1 1 5 self))
					(else (messager say: 16 2 16 0 self))
				)
			)
			(2
				(if (not local18)
					(messager say: 16 2 18)
					(ego normalize:)
					(theGame handsOn:)
					(self dispose:)
				else
					(messager say: 16 2 1 6 self)
				)
			)
			(3
				(lifeGuard hide:)
				(ego
					view: 237
					setSpeed: 6
					loop: 2
					setCel: 0
					setCycle: End self
				)
			)
			(4
				(lifeGuard show:)
				(ego get: 20 normalize: 236 ignoreActors: 0)
				(theGame handsOn: changeScore: 6 166)
				(self dispose:)
			)
		)
	)
)

(instance openTowerDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 171 53 self)
			)
			(1
				(ego
					view: 238
					setSpeed: 12
					loop: 2
					setCel: 0
					setCycle: End self
				)
				(towerDoor setCycle: End)
				(sFx number: 32 loop: 1 play:)
			)
			(2
				(ego
					normalize: 236 3
					setPri: 37
					setMotion: MoveTo 169 47 self
				)
			)
			(3
				(towerDoor init: setCel: 5 setCycle: Beg self)
			)
			(4
				(sFx number: 33 loop: 1 play:)
				(ego
					view: 238
					ignoreActors: 1
					setLoop: 1 1
					setSpeed: 12
					setCycle: Fwd
					setMotion: MoveTo 169 1 self
				)
			)
			(5
				(if (Btst 168) (Bset 72))
				(theMusic fade:)
				(curRoom newRoom: 270)
			)
		)
	)
)

(instance takeADive of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset 27)
				(local0 type: 3)
				(local1 type: 3)
				(if (poolBar script?) ((poolBar script?) dispose:))
				(ego view: 238 hide: setLoop: 0 1 posn: 168 0)
				(= ticks 210)
			)
			(1
				(sFx number: 271 loop: 1 play: self fade: 127 5 35 0)
				(ego
					show:
					moveSpeed: 0
					setPri: 199
					setCycle: 0
					setLooper: 0
					setLoop: 0 1
					setMotion: MoveTo 168 62 self
				)
			)
			(2)
			(3
				(ego setCycle: 0 setLoop: 3 1 setCel: 0)
				(= ticks 9)
			)
			(4
				(ego setMotion: MoveTo 168 72 self)
			)
			(5
				(ego setSpeed: 12 setCycle: End self)
				(sFx number: 231 loop: 1 play:)
			)
			(6 (ego hide:) (= ticks 300))
			(7
				(ego show: setLoop: 5 1 setCel: 0 setCycle: End self)
			)
			(8
				(messager sayRange: 1 0 2 1 2 self)
			)
			(9
				(theGame handsOn:)
				(Bset 90)
				(ego
					normalize: 229
					cycleSpeed: 12
					moveSpeed: 6
					ignoreActors: 0
					setCycle: poolWalker 2291
					xStep: 2
					yStep: 2
				)
				(if (cast contains: barLady) (barLady dispose:))
				(if (cast contains: merr)
					(merr ignoreActors: 1)
					(kenny ignoreActors: 1)
				else
					(poolBar ignoreActors: 0 baseSetter: poolBase)
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance giveBackKey of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					normalize: 236
					ignoreActors: 0
					setCycle: Walk
					setMotion: PolyPath 30 66 self
				)
			)
			(1
				(if (Btst 90)
					(messager sayRange: 1 0 2 3 4 self)
				else
					(messager say: 16 50 21 0 self)
				)
			)
			(2
				(ego
					view: 237
					setSpeed: 6
					loop: 2
					setCel: 0
					setCycle: End self
				)
			)
			(3
				(ego put: 20 normalize: 236 1 ignoreActors: 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance exitEast of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= ticks 30)
			)
			(1
				(ego setMotion: MoveTo 330 (ego y?) self)
			)
			(2
				(if (Btst 168) (Bset 72))
				(theMusic fade:)
				(curRoom newRoom: 860)
			)
		)
	)
)

(instance exitWest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= ticks 30)
			)
			(1
				(ego setMotion: MoveTo -10 (ego y?) self)
			)
			(2
				(if (Btst 168) (Bset 72))
				(theMusic fade:)
				(curRoom newRoom: 820)
			)
		)
	)
)

(instance enterFromHall of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego posn: 39 44 setMotion: PolyPath 39 47 self)
			)
			(1 (= cycles 2))
			(2
				(if
				(and (not (Btst 109)) (Btst 55) (not (Btst 8)))
					(messager say: 0 0 22 0 self)
					(Bset 109)
				else
					(self cue:)
				)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance enterFromBeach of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(ego posn: 330 52 setMotion: MoveTo 312 52 self)
			)
			(1 (= cycles 2))
			(2
				(if
				(and (not (Btst 109)) (Btst 55) (not (Btst 8)))
					(messager say: 0 0 22 0 self)
					(Bset 109)
				else
					(self cue:)
				)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance enterFromAerobics of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(Bclr 9)
				(ego
					view: 232
					posn: 300 41
					init:
					setStep: 2 1
					setMotion: PolyPath 295 45 self
				)
			)
			(1
				(aerobicsDoor setCycle: Beg self)
			)
			(2
				(sFx number: 33 loop: 1 play: setVol: 126)
				(ego normalize: 232 2)
				(if
				(and (not (Btst 109)) (Btst 55) (not (Btst 8)))
					(messager say: 0 0 22 0 self)
					(Bset 109)
				else
					(self cue:)
				)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance enterFromWoods of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego posn: -10 55 setMotion: MoveTo 5 55 self)
			)
			(1 (= cycles 2))
			(2
				(if
				(and (not (Btst 109)) (Btst 55) (not (Btst 8)))
					(messager say: 0 0 22 0 self)
					(Bset 109)
				else
					(self cue:)
				)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance wateryDeath of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 160 103 self)
			)
			(1 (messager say: 2 4 0 1 self))
			(2
				(ego setMotion: MoveTo 160 99 self)
			)
			(3
				(ego
					view: 231
					loop: 2
					setCel: 0
					setSpeed: 6
					setCycle: End self
				)
			)
			(4
				(sFx number: 231 loop: 1 play:)
				(messager sayRange: 2 4 0 2 4 self)
			)
			(5 (EgoDead 5 self))
			(6
				(ego posn: 160 110 normalize: 232 0 1 ignoreActors: 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance jumpInPool of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 160 110 self)
			)
			(1
				(ego setMotion: MoveTo 160 100 self)
			)
			(2
				(ego
					normalize: 229
					ignoreActors: 0
					cycleSpeed: 12
					moveSpeed: 6
					setCycle: poolWalker 2291
				)
				(if (cast contains: merr)
					(merr ignoreActors: 1)
					(kenny ignoreActors: 1)
				else
					(poolBar ignoreActors: 0 baseSetter: poolBase)
				)
				(local0 type: 3)
				(local1 type: 3)
				(Bset 27)
				(ego xStep: 2 yStep: 2)
				(if (poolBar script?)
					((poolBar script?) dispose:)
					(poolBar ignoreActors: 0)
					(merr ignoreActors: 1)
					(kenny ignoreActors: 1)
				)
				(self dispose:)
			)
		)
	)
)

(instance changeClothes of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (not register) (= state 5))
				(ego setMotion: PolyPath 162 133 self)
			)
			(1
				(messager say: 21 47 6 0 self)
			)
			(2
				(ego setMotion: PolyPath 160 122 self)
			)
			(3 (ego setHeading: 310 self))
			(4 (= cycles 2))
			(5
				(theGame handsOn:)
				(self dispose:)
			)
			(6
				(if (== (ego view?) 236)
					(self setScript: wearSuit self)
				else
					(self setScript: wearSwimsuit self)
				)
			)
			(7
				(ego setMotion: PolyPath 159 112 self)
			)
			(8
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance wearSuit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					view: 235
					setSpeed: 12
					loop: 4
					setCel: 4
					setCycle: Beg self
				)
			)
			(1
				(ego loop: 2 setCel: 0 setCycle: End self)
			)
			(2
				(ego normalize: 232)
				(Bclr 74)
				(UpdateScreenItem ((ScriptID 92 3) view: 1900))
				(self dispose:)
			)
		)
	)
)

(instance wearSwimsuit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					view: 235
					setSpeed: 12
					loop: 0
					setCel: 0
					setCycle: End self
				)
			)
			(1
				(ego loop: 1 setCel: 0 setCycle: End self)
			)
			(2
				(ego loop: 3 setCel: 0 setCycle: End self)
			)
			(3
				(ego loop: 4 setCel: 0 setCycle: End self)
			)
			(4
				(Bset 74)
				(UpdateScreenItem ((ScriptID 92 3) view: 1901))
				(theGame changeScore: 12 165)
				(ego normalize: 236)
				(self dispose:)
			)
		)
	)
)

(instance getOnBeaver of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 160 110 self)
			)
			(1
				(ego setMotion: MoveTo 160 100 self)
			)
			(2
				(Bset 27)
				(if (poolBar script?) ((poolBar script?) dispose:))
				(if (cast contains: kenny)
					(kenny ignoreActors: 1)
					(merr ignoreActors: 1)
				else
					(poolBar ignoreActors: 0 baseSetter: poolBase)
				)
				(local0 type: 3)
				(local1 type: 3)
				(ego
					normalize: 239
					cycleSpeed: 12
					moveSpeed: 6
					ignoreActors: 0
					posn: 160 97
					xStep: 2
					yStep: 2
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance getOffBeaver of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(local0 type: 2)
				(local1 type: 2)
				(ego
					normalize: 236
					ignoreActors: 0
					actions: egoActions
					approachX: 162
					approachY: 133
					approachVerbs: 47 45
				)
				(Bclr 27)
				(ego setCycle: Walk setMotion: MoveTo 160 110 self)
			)
			(1
				(poolBar ignoreActors: 1)
				(if (cast contains: merr)
					(kenny ignoreActors: 1)
					(merr ignoreActors: 1)
				)
				(ego normalize:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance girlDiver of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 3))
			(1
				(bather4
					view: 233
					loop: 10
					setCel: 4
					cycleSpeed: 12
					ignoreActors: 0
					approachVerbs: 0
					setCycle: Beg self
				)
			)
			(2
				(client
					setLoop: -1
					setCycle: Walk
					cycleSpeed: 6
					setMotion: MoveTo 31 68 self
				)
			)
			(3 (= ticks 120))
			(4
				(lifeGuard hide:)
				(client
					view: 237
					cycleSpeed: 12
					loop: 0
					setCel: 0
					setCycle: End self
				)
			)
			(5
				(= local18 0)
				(lifeGuard show:)
				(client
					view: 233
					cycleSpeed: 6
					setCycle: Walk
					setMotion: PolyPath 171 52 self
				)
			)
			(6
				(client setMotion: MoveTo 171 52 self)
			)
			(7
				(towerDoor hide:)
				(client
					view: 237
					loop: 6
					cycleSpeed: 12
					setCel: 0
					setCycle: End self
				)
				(sFx number: 32 loop: 1 play:)
			)
			(8
				(client
					view: 233
					setPri: 37
					setCycle: Walk
					cycleSpeed: 6
					setMotion: MoveTo 169 48 self
				)
			)
			(9
				(towerDoor show:)
				(sFx number: 33 loop: 1 play:)
				(client
					view: 237
					setLoop: 4 1
					setCycle: Fwd
					ignoreActors: 1
					setMotion: MoveTo 169 0 self
				)
			)
			(10
				(client setLoop: -1 hide:)
				(= seconds 7)
			)
			(11
				(client
					view: 237
					show:
					setLoop: 9 1
					setCel: 0
					setCycle: 0
					setPri: 55
					moveSpeed: 0
					setMotion: MoveTo 169 74 self
				)
			)
			(12
				(client
					loop: 7
					setCel: 0
					setPri: -1
					ignoreActors: 0
					setCycle: End self
				)
				(sFx number: 231 loop: 1 play:)
			)
			(13
				(client hide:)
				(= ticks 180)
			)
			(14
				(client
					show:
					posn: 92 56
					view: 233
					setLoop: 9 1
					setCel: 0
					moveSpeed: 6
					cycleSpeed: 12
					setCycle: End self
				)
			)
			(15
				(client
					setLoop: -1
					setCycle: Walk
					cycleSpeed: 6
					setMotion: PolyPath 31 68 self
				)
			)
			(16
				(lifeGuard hide:)
				(client
					view: 237
					loop: 0
					setCel: 0
					cycleSpeed: 12
					setCycle: End self
				)
			)
			(17
				(= local18 1)
				(lifeGuard show:)
				(client
					view: 233
					setCycle: Walk
					cycleSpeed: 6
					setMotion: MoveTo 92 52 self
				)
			)
			(18
				(client
					loop: 10
					cycleSpeed: 12
					setCel: 0
					setCycle: End self
				)
			)
			(19
				(client
					view: 230
					cycleSpeed: 6
					loop: 8
					cel: 5
					ignoreActors: 1
					setPri: 50
					approachVerbs: 1 4 6 2
				)
				(self dispose:)
			)
		)
	)
)

(instance guyDiver of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 3))
			(1
				(bather3
					view: 234
					loop: 10
					cycleSpeed: 12
					setCel: 4
					ignoreActors: 0
					approachVerbs: 0
					setCycle: Beg self
				)
			)
			(2
				(client
					setLoop: -1
					setCycle: Walk
					cycleSpeed: 6
					setMotion: DPath 224 52 194 50 124 51 78 57 31 68 self
				)
			)
			(3
				(lifeGuard hide:)
				(bather3
					view: 237
					loop: 1
					cycleSpeed: 12
					setCel: 0
					setCycle: End self
				)
			)
			(4
				(= local18 0)
				(lifeGuard show:)
				(bather3
					view: 234
					setCycle: Walk
					cycleSpeed: 6
					setMotion: DPath 82 56 123 51 171 52 self
				)
			)
			(5
				(towerDoor hide:)
				(bather3
					view: 237
					loop: 5
					setCel: 0
					cycleSpeed: 12
					setCycle: End self
				)
				(sFx number: 32 loop: 1 play:)
			)
			(6
				(bather3
					view: 234
					setCycle: Walk
					setPri: 37
					cycleSpeed: 6
					setMotion: MoveTo 169 48 self
				)
			)
			(7
				(sFx number: 33 loop: 1 play:)
				(towerDoor show:)
				(bather3
					view: 237
					setLoop: 3 1
					setCycle: Fwd
					ignoreActors: 1
					setMotion: MoveTo 169 0 self
				)
			)
			(8
				(bather3 setLoop: -1 hide:)
				(= seconds 7)
			)
			(9
				(bather3
					view: 237
					show:
					setLoop: 9 1
					setCel: 1
					setCycle: 0
					setPri: 55
					moveSpeed: 0
					setMotion: MoveTo 169 74 self
				)
			)
			(10
				(bather3
					loop: 8
					setCel: 0
					setPri: -1
					cycleSpeed: 12
					ignoreActors: 0
					setCycle: End self
				)
				(sFx number: 231 loop: 1 play:)
			)
			(11
				(bather3 hide:)
				(= ticks 180)
			)
			(12
				(bather3
					show:
					posn: 92 56
					view: 234
					setLoop: 9 1
					setCel: 0
					moveSpeed: 6
					setCycle: End self
				)
			)
			(13
				(bather3
					setLoop: -1
					setCycle: Walk
					cycleSpeed: 6
					setMotion: PolyPath 31 68 self
				)
			)
			(14
				(lifeGuard hide:)
				(bather3
					view: 237
					loop: 1
					setCel: 0
					cycleSpeed: 12
					setCycle: End self
				)
			)
			(15
				(= local18 1)
				(lifeGuard show:)
				(bather3
					view: 234
					setCycle: Walk
					cycleSpeed: 6
					setMotion: DPath 78 57 124 51 194 50 224 52 279 63 self
				)
			)
			(16
				(bather3
					loop: 10
					setCel: 0
					cycleSpeed: 12
					setCycle: End self
				)
			)
			(17
				(bather3
					view: 230
					loop: 8
					cycleSpeed: 6
					cel: 6
					ignoreActors: 1
					setPri: 61
					approachVerbs: 1 4 6 2
				)
				(self dispose:)
			)
		)
	)
)

(instance toAerobics of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(aerobicsDoor setCycle: End self)
				(sFx number: 32 loop: 1 setVol: 127 play:)
				(theMusic fade:)
			)
			(1
				(ego setMotion: MoveTo 304 40 self)
			)
			(2 (curRoom newRoom: 440))
		)
	)
)

(instance poolBar of Actor
	(properties
		noun 4
		x 212
		y 98
		view 230
		loop 2
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return 1))
			(
				(and
					(& (event type?) $4000)
					(self onMe: event)
					(self isNotHidden:)
				)
				(= pEventMessage (event message?))
				(event claimed: 1)
				(ego
					setMotion: (poolyPath new:) approachX (+ (ego z?) approachY) self
				)
			)
		)
		(return (event claimed?))
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(1
					(cond 
						((== (ego view?) 239)
							(if (Btst 72)
								(curRoom newRoom: 240)
							else
								(messager say: 4 1 4)
								(return 1)
							)
						)
						((OneOf (ego view?) 2291 229) (messager say: 4 1 13) (return 1))
						((not (Btst 168)) (curRoom setScript: handOnPoolBar))
						(else (super doVerb: theVerb))
					)
				)
				(4
					(cond 
						((== (ego view?) 239)
							(if (Btst 72)
								(curRoom newRoom: 240)
							else
								(messager say: 4 1 4)
								(return 1)
							)
						)
						((OneOf (ego view?) 2291 229) (messager say: 4 4 13) (return 1))
						((not (Btst 168)) (curRoom setScript: handOnPoolBar))
						(else (super doVerb: theVerb))
					)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
	
	(method (onMe theObjOrX)
		(return
			(if (super onMe: theObjOrX)
				(= approachX (- (self x?) 20))
				(= approachY (+ (self y?) 15))
				1
			else
				0
			)
		)
	)
	
	(method (cue)
		(self doVerb: pEventMessage)
	)
)

(instance bungeeJumper of Prop
	(properties
		x 170
		view 228
	)
	
	(method (cue)
		(self dispose:)
	)
)

(instance barLady of Actor
	(properties
		noun 28
		view 228
		loop 2
	)
	
	(method (doVerb theVerb)
		(poolBar doVerb: theVerb)
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance towerDoor of Prop
	(properties
		noun 5
		approachX 166
		approachY 46
		x 161
		y 27
		view 2300
	)
	
	(method (doVerb theVerb)
		(return
			(if (Btst 27)
				(messager say: 1 0 13)
				(return 1)
			else
				(switch theVerb
					(4
						(sFx number: 34 loop: 1 play: setVol: 127)
						(super doVerb: theVerb)
					)
					(50
						(curRoom setScript: openTowerDoor)
					)
					(52
						(sFx number: 34 loop: 1 play: setVol: 127)
						(super doVerb: theVerb)
					)
					(else  (super doVerb: theVerb))
				)
			)
		)
	)
)

(instance bather1 of View
	(properties
		noun 12
		approachX 67
		approachY 50
		x 59
		y 52
		view 230
		loop 8
	)
	
	(method (doVerb theVerb)
		(return
			(cond 
				((Btst 27) (messager say: 1 0 13) (return 1))
				((OneOf theVerb 1 2) (super doVerb: theVerb))
				(else (curRoom doVerb: theVerb))
			)
		)
	)
)

(instance bather2 of View
	(properties
		noun 13
		approachX 209
		approachY 44
		x 223
		y 44
		view 230
		loop 8
		cel 1
	)
	
	(method (doVerb theVerb)
		(return
			(cond 
				((Btst 27) (messager say: 1 0 13) (return 1))
				((OneOf theVerb 1 2) (super doVerb: theVerb))
				(else (curRoom doVerb: theVerb))
			)
		)
	)
)

(instance bather3 of Actor
	(properties
		noun 14
		approachX 265
		approachY 45
		x 279
		y 63
		view 230
		loop 8
		cel 6
	)
	
	(method (doVerb theVerb)
		(return
			(cond 
				((Btst 27) (messager say: 1 0 13) (return 1))
				((OneOf theVerb 1 2) (super doVerb: theVerb))
				(else (curRoom doVerb: theVerb))
			)
		)
	)
)

(instance bather4 of Actor
	(properties
		noun 15
		approachX 108
		approachY 51
		x 92
		y 52
		view 230
		loop 8
		cel 5
	)
	
	(method (doVerb theVerb)
		(return
			(cond 
				((Btst 27) (messager say: 1 0 13) (return 1))
				((OneOf theVerb 1 2) (super doVerb: theVerb))
				(else (curRoom doVerb: theVerb))
			)
		)
	)
)

(instance lifeGuard of View
	(properties
		noun 16
		approachX 31
		approachY 69
		x 21
		y 45
		view 230
		loop 7
	)
	
	(method (doVerb theVerb)
		(return
			(if (Btst 27)
				(messager say: 1 0 13)
				(return 1)
			else
				(switch theVerb
					(2
						(cond 
							((== (ego view?) 232) (messager say: 16 2) (return 1))
							((ego has: 20) (curRoom setScript: giveBackKey))
							(else (curRoom setScript: getTowerKey))
						)
					)
					(50
						(curRoom setScript: giveBackKey)
					)
					(else  (super doVerb: theVerb))
				)
			)
		)
	)
)

(instance beaver of View
	(properties
		noun 22
		approachX 205
		approachY 45
		x 205
		y 41
		view 230
		loop 6
	)
	
	(method (doVerb theVerb)
		(return
			(cond 
				((Btst 27) (messager say: 1 0 13) (return 1))
				((== theVerb 5)
					(ego get: 2)
					(theGame changeScore: 3 167)
					(beavFeat dispose:)
					(messager say: 22 5)
					(self dispose:)
					(return 1)
				)
				(else (super doVerb: theVerb))
			)
		)
	)
)

(instance kenny of Actor
	(properties
		noun 26
		view 230
		loop 9
		cel 1
	)
)

(instance merr of Actor
	(properties
		noun 27
		view 230
		loop 9
	)
	
	(method (doit)
		(if (poolBar mover?)
			(merr posn: (+ (poolBar x?) 19) (+ (poolBar y?) 10))
			(kenny posn: (- (poolBar x?) 32) (- (poolBar y?) 6))
		)
		(super doit: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (Btst 7)
					(messager say: 27 1 20)
				else
					(super doVerb: theVerb)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance aerobicsDoor of Prop
	(properties
		noun 11
		approachX 300
		approachY 43
		x 297
		y 20
		view 2300
		loop 1
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 4)
	)
	
	(method (doVerb theVerb)
		(if (Btst 27) (messager say: 1 0 13) (return 1))
		(return
			(if (== theVerb 4)
				(cond 
					((ego has: 20) (curRoom setScript: gimmeMyKeyDammit))
					((== (ego view?) 236) (curRoom setScript: getDecent 0 1))
					(else
						(if (Btst 168) (Bset 72))
						(curRoom setScript: toAerobics)
					)
				)
			else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance poolEdge of Feature
	(properties
		noun 1
		approachX 160
		approachY 99
	)
	
	(method (doVerb theVerb)
		(curRoom doVerb: theVerb)
	)
)

(instance leftDoor of Feature
	(properties
		noun 11
		nsLeft 28
		nsTop 18
		nsRight 48
		nsBottom 49
		approachX 37
		approachY 45
		x 43
		y 40
	)
	
	(method (doVerb theVerb)
		(if (Btst 27) (messager say: 1 0 13) (return 1))
		(return
			(if (OneOf theVerb 4 3)
				(cond 
					((ego has: 20) (curRoom setScript: gimmeMyKeyDammit))
					((== (ego view?) 236) (curRoom setScript: getDecent 0 0))
					(else (curRoom setScript: enterHall))
				)
			else
				0
			)
		)
	)
)

(instance chairFtr of Feature
	(properties
		noun 3
	)
	
	(method (doVerb theVerb)
		(return
			(if (Btst 27)
				(messager say: 1 0 13)
				(return 1)
			else
				(switch theVerb
					(4
						(if (== (ego view?) 232)
							(super doVerb: theVerb)
						else
							(messager say: 3 4 1)
							(return 1)
						)
					)
					(else  (super doVerb: theVerb))
				)
			)
		)
	)
	
	(method (onMe theObjOrX)
		(if
			(or
				(local2 onMe: (theObjOrX x?) (theObjOrX y?))
				(local3 onMe: (theObjOrX x?) (theObjOrX y?))
			)
		else
			(local4 onMe: (theObjOrX x?) (theObjOrX y?))
		)
	)
)

(instance poolFtr of Feature
	(properties
		noun 2
		approachX 160
		approachY 99
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(4
					(cond 
						((ego has: 20) (messager say: 2 4 6) (return 1))
						((not (Btst 27))
							(curRoom
								setScript: (if (== (ego view?) 232) wateryDeath else jumpInPool)
							)
						)
						(else (curRoom setScript: getOffBeaver))
					)
				)
				(6
					(if (Btst 27)
						(messager say: 2 6 13)
						(return 1)
					else
						(super doVerb: theVerb)
					)
				)
				(49
					(cond 
						((== (ego view?) 239) (curRoom setScript: getOffBeaver))
						((Btst 27) (ego view: 239) (return 1))
						((ego has: 20) (messager say: 2 4 6) (return 1))
						(else
							(curRoom
								setScript: (if (== (ego view?) 232) wateryDeath else getOnBeaver)
							)
						)
					)
				)
				(35
					(theGame changeScore: 6 250)
					(= global185 3)
					((inventory at: 39) cue:)
					(super doVerb: theVerb)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
	
	(method (onMe theObjOrX)
		(return
			(if (local1 onMe: (theObjOrX x?) (theObjOrX y?))
				(if (Btst 27)
					(= approachX 160)
					(= approachY 99)
				else
					(= approachX 160)
					(= approachY 105)
				)
				1
			else
				0
			)
		)
	)
)

(instance window1 of Feature
	(properties
		noun 6
		nsLeft 75
		nsTop 17
		nsRight 85
		nsBottom 31
		approachX 80
		approachY 42
		x 85
		y 31
	)
	
	(method (doVerb theVerb)
		(return
			(if (Btst 27)
				(messager say: 1 0 13)
				(return 1)
			else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance window2 of Feature
	(properties
		noun 7
		nsLeft 105
		nsTop 15
		nsRight 118
		nsBottom 29
		approachX 111
		approachY 38
		x 115
		y 29
	)
	
	(method (doVerb theVerb)
		(return
			(if (Btst 27)
				(messager say: 1 0 13)
				(return 1)
			else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance window3 of Feature
	(properties
		noun 8
		nsLeft 200
		nsTop 12
		nsRight 214
		nsBottom 29
		approachX 209
		approachY 39
		x 210
		y 29
	)
	
	(method (doVerb theVerb)
		(return
			(if (Btst 27)
				(messager say: 1 0 13)
				(return 1)
			else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance window4 of Feature
	(properties
		noun 9
		nsLeft 250
		nsTop 17
		nsRight 262
		nsBottom 34
		approachX 256
		approachY 42
		x 262
		y 34
	)
	
	(method (doVerb theVerb)
		(return
			(if (Btst 27)
				(messager say: 1 0 13)
				(return 1)
			else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance bushes of Feature
	(properties
		noun 17
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						0
						138
						0
						59
						9
						60
						18
						69
						22
						103
						41
						118
						131
						121
						137
						125
						154
						122
						177
						122
						185
						126
						197
						121
						220
						120
						226
						123
						288
						118
						291
						86
						296
						77
						298
						60
						312
						53
						319
						53
						319
						138
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(4
					(if (Btst 27)
						(messager say: 1 0 15)
						(return 1)
					else
						(super doVerb: theVerb)
					)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance barWindow of Feature
	(properties
		noun 23
		y 200
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 57 87 60 99 51 107 45 97
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance beavFeat of Feature
	(properties)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 216 35 218 43 191 43 194 35
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(beaver doVerb: theVerb)
	)
)

(instance egoActions of Actions
	(properties)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(4
					(if (== (ego view?) 236)
						(messager say: 21 4 1)
						(return 1)
					else
						(super doVerb: theVerb)
					)
				)
				(47
					(cond 
						((Btst 27) (messager say: 21 47 1) (return 1))
						((ego has: 20) (curRoom setScript: changeClothes 0 1) (return 1))
						(else (curRoom setScript: changeClothes 0 0) (return 1))
					)
				)
				(45
					(if (ego has: 35)
						(ego doVerb: 47)
						(return 1)
					else
						(messager say: 21 45 7)
						(return 1)
					)
				)
				(49
					(if (Btst 27)
						(ego view: 239)
						(return 1)
					else
						(super doVerb: theVerb)
					)
				)
				(1
					(if (Btst 27)
						(if (== (ego view?) 239)
							(messager say: 21 1 15)
						else
							(messager say: 21 1 13)
						)
						(return 1)
					else
						(super doVerb: theVerb)
					)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance sFx of Sound
	(properties)
)

(instance poolBase of Code
	(properties)
	
	(method (doit)
		(poolBar
			brTop: (poolBar nsTop?)
			brBottom: (poolBar nsBottom?)
			brLeft: (poolBar nsLeft?)
			brRight: (poolBar nsRight?)
		)
		(SetNowSeen poolBar)
	)
)

(instance merrSetter of Code
	(properties)
	
	(method (doit)
		(merr
			brTop: (merr nsTop?)
			brBottom: (+ (merr nsBottom?) 5)
			brLeft: (merr nsLeft?)
			brRight: (merr nsRight?)
		)
		(SetNowSeen merr)
	)
)

(instance kennySetter of Code
	(properties)
	
	(method (doit)
		(kenny
			brTop: (kenny nsTop?)
			brBottom: (kenny nsBottom?)
			brLeft: (kenny nsLeft?)
			brRight: (kenny nsRight?)
		)
		(SetNowSeen kenny)
	)
)

(instance poolWalker of StopWalk
	(properties
		vWalking 229
		vStopped 2291
	)
)

(instance poolyPath of PolyPath
	(properties)
	
	(method (doit)
		(if (ego isBlocked:)
			(= completed 1)
			(self motionCue:)
		else
			(super doit: &rest)
		)
	)
)
