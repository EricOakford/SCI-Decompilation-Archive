;;; Sierra Script 1.0 - (do not remove this comment)
(script# 350)
(include sci.sh)
(use Main)
(use fileScr)
(use LarryRoom)
(use CycleBet)
(use PolyFeature)
(use PolyPath)
(use Polygon)
(use Feature)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm350 0
)

(local
	gEgoView
	[local1 20] = [103 124 148 170 191 97 121 148 172 196 90 118 148 174 203 83 114 148 177 210]
	[local21 20] = [97 97 97 97 97 106 106 106 106 106 118 118 118 118 118 130 130 130 130 130]
	[local41 20]
	[local61 10]
	local71
	local72
	local73
	local74
	local75
	local76
	local77
	local78
	local79
	local80
	local81
	local82
	local83
	local84
	local85
	local86
	local87
)
(instance rm350 of LarryRoom
	(properties
		noun 1
		picture 350
		horizon 0
	)
	
	(method (init)
		(if (theMusic2 handle?) (theMusic2 stop:))
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init: 56 148 274 148 226 86 200 81 146 81 82 98 39 147
					yourself:
				)
		)
		(= local73
			((Polygon new:)
				type: 0
				init: 67 59 54 51 54 36 70 48
				yourself:
			)
		)
		(= local74
			((Polygon new:)
				type: 0
				init: 102 40 102 26 113 26 113 40
				yourself:
			)
		)
		(= local75
			((Polygon new:)
				type: 0
				init: 130 39 130 25 139 25 139 39
				yourself:
			)
		)
		(= local76
			((Polygon new:)
				type: 0
				init: 223 53 223 43 238 34 238 48
				yourself:
			)
		)
		(= local77
			((Polygon new:)
				type: 0
				init: 243 58 243 48 258 40 258 52 244 58
				yourself:
			)
		)
		(= local78
			((Polygon new:)
				type: 0
				init: 267 69 267 59 282 53 277 67 268 69
				yourself:
			)
		)
		(= local79
			((Polygon new:)
				type: 0
				init: 58 70 59 64 67 64 67 71
				yourself:
			)
		)
		(= local80
			((Polygon new:)
				type: 0
				init: 101 59 101 54 111 54 111 60 101 60
				yourself:
			)
		)
		(= local81
			((Polygon new:)
				type: 0
				init: 130 59 130 53 137 53 137 59 130 60
				yourself:
			)
		)
		(= local82
			((Polygon new:)
				type: 0
				init: 52 65 51 55 59 55 59 65 53 65
				yourself:
			)
		)
		(= local83
			((Polygon new:)
				type: 0
				init: 84 48 85 39 92 39 91 49 85 48
				yourself:
			)
		)
		(= local84
			((Polygon new:)
				type: 0
				init: 116 48 116 39 124 39 124 49
				yourself:
			)
		)
		(= local85
			((Polygon new:)
				type: 0
				init: 233 63 234 55 241 56 239 66
				yourself:
			)
		)
		(= local86
			((Polygon new:)
				type: 0
				init: 256 71 257 62 265 62 263 71 257 71
				yourself:
			)
		)
		(knobs init:)
		(super init: &rest)
		(ego init: normalize: actions: egoActions posn: 152 156)
		(mudBathDoor init:)
		(if (not (Btst 191))
			(looseTile init: approachVerbs: 1 4 5)
		)
		(fountHandle init: approachVerbs: 4)
		(nozzles init:)
		(racks init:)
		((fountOJoy new:)
			init:
			ignoreActors: 1
			hide:
			setScript: (fountainScript new:) 0 0
		)
		((fountOJoy new:)
			init:
			ignoreActors: 1
			hide:
			setScript: (fountainScript new:) 0 1
		)
		((fountOJoy new:)
			init:
			ignoreActors: 1
			hide:
			setScript: (fountainScript new:) 0 2
		)
		((fountOJoy new:)
			init:
			ignoreActors: 1
			hide:
			setScript: (fountainScript new:) 0 3
		)
		((fountOJoy new:)
			init:
			ignoreActors: 1
			hide:
			setScript: (fountainScript new:) 0 4
		)
		(if (Random 0 1)
			(showerGuy1 init: setScript: shower1)
			(towel1 init:)
			(water1 init: setPri: 90 setCycle: Fwd)
			(sFx3 number: 352 play: setVol: 127 setLoop: -1)
		)
		(if (Random 0 1)
			(showerGuy2 init: setScript: shower2)
			(guy2Towel init:)
			(water2 init: setPri: 88 setCycle: Fwd)
			(sFx3 number: 352 play: setVol: 127 setLoop: -1)
		)
		(showerHandle init:)
		(switch prevRoomNum
			(340
				(ego posn: 152 156)
				(curRoom setScript: enterFromLocker)
			)
			(360
				(ego posn: 237 106)
				(curRoom setScript: fromHole)
			)
			(else 
				(ego posn: 174 72)
				(curRoom setScript: enterFromBath)
			)
		)
		(proc79_11 346 347)
	)
	
	(method (doit &tmp egoEdgeHit)
		(= egoEdgeHit (ego edgeHit?))
		(cond 
			(script)
			((== egoEdgeHit 3) (curRoom setScript: toLockerRoom))
		)
		(cond 
			(
				(and
					(== (ego view?) 900)
					(ego mover?)
					(== (ego cel?) 4)
				)
				(if (== local71 0)
					(= local71 1)
					(shoeFx number: 347 loop: 1 play:)
				)
			)
			(
				(and
					(== (ego view?) 900)
					(ego mover?)
					(== (ego cel?) 1)
					(== local71 1)
				)
				(= local71 0)
				(shoeFx number: 346 loop: 1 play:)
			)
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(shoeFx number: 0 dispose:)
		(proc79_12 346 347)
		(local73 dispose:)
		(local74 dispose:)
		(local75 dispose:)
		(local76 dispose:)
		(local77 dispose:)
		(local78 dispose:)
		(local79 dispose:)
		(local80 dispose:)
		(local81 dispose:)
		(super dispose:)
	)
)

(instance shower1 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(showerGuy1 loop: 0 setCycle: Fwd)
				(= seconds (Random 1 5))
			)
			(1
				(showerGuy1 loop: 1 setCel: 0 setCycle: End self)
			)
			(2
				(showerGuy1 setCycle: CycleBet 3 5 -1)
				(= seconds (Random 1 5))
			)
			(3 (self init:))
		)
	)
)

(instance shower2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(showerGuy2 loop: 0 setCel: 0 setCycle: End self)
			)
			(1
				(showerGuy2 setCycle: CycleBet 3 5 -1)
				(= seconds (Random 1 5))
			)
			(2
				(showerGuy2 setCycle: Beg self)
			)
			(3
				(showerGuy2 loop: 1 setCel: 0 setCycle: End self)
			)
			(4
				(showerGuy2 setCycle: CycleBet 4 5 -1)
				(= seconds (Random 1 5))
			)
			(5
				(showerGuy2 setCycle: Beg self)
			)
			(6 (self init:))
		)
	)
)

(instance toMudBath of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 173 85 self)
			)
			(1
				(= gEgoView (ego view?))
				(ego setHeading: 0 self)
			)
			(2
				(if (== gEgoView 900)
					(ego view: 901 loop: 6 setCel: 0 setCycle: End self)
				else
					(self cue:)
				)
			)
			(3
				(sFx number: 32 loop: 1 play: setVol: 127)
				(mudBathDoor setCycle: End self)
			)
			(4
				(ego normalize: gEgoView setMotion: MoveTo 174 72 self)
			)
			(5 (curRoom newRoom: 400))
		)
	)
)

(instance toLockerRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo (ego x?) 200 self)
			)
			(1 (curRoom newRoom: 340))
		)
	)
)

(instance larryShowersMud of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset 65)
				(ego setMotion: PolyPath 228 99 self)
			)
			(1
				(ego
					view: 357
					setSpeed: 12
					loop: 0
					setCel: 0
					setCycle: End self
				)
			)
			(2
				(larryTowel init:)
				(ego loop: 1 setCel: 0 setCycle: CT 4 1 self)
			)
			(3
				(sFx2 number: 355 loop: 1 play:)
				(ego setCycle: End)
				(= ticks 30)
			)
			(4
				(sFx2 number: 355 loop: 1 play:)
				(ego setCel: 4 setCycle: End)
				(= ticks 30)
			)
			(5
				(sFx2 number: 355 loop: 1 play:)
				(ego setCel: 4 setCycle: End)
				(= ticks 30)
			)
			(6
				(sFx2 number: 352 loop: -1 play:)
				(theGame changeScore: 3 193)
				(showerWater init: setPri: 190 setCycle: Fwd)
				(ego setLoop: 2 setCel: 0 setCycle: CT 4 1 self)
			)
			(7 (ego cel: 3) (= ticks 20))
			(8 (ego cel: 4) (= ticks 20))
			(9 (ego cel: 3) (= ticks 20))
			(10 (ego cel: 4) (= ticks 20))
			(11
				(ego setLoop: 3 setCel: 0 setCycle: Fwd)
				(= seconds 5)
			)
			(12
				(ego loop: 4 setCel: 0 setCycle: End self)
			)
			(13
				(ego view: 352 loop: 2 setCel: 0 setCycle: Fwd)
				(= seconds 3)
			)
			(14
				(ego view: 352 loop: 1 setCel: 0 setCycle: End self)
			)
			(15
				(sFx2 number: 355 loop: 1 play:)
				(ego setCel: 4 setCycle: End)
				(= ticks 30)
			)
			(16
				(sFx2 number: 355 loop: 1 play:)
				(ego setCel: 4 setCycle: End)
				(= ticks 30)
			)
			(17
				(sFx2 number: 355 loop: 1 play:)
				(= ticks 30)
			)
			(18
				(showerWater dispose:)
				(larryTowel dispose:)
				(ego view: 352 loop: 4 setCel: 0 setCycle: End self)
			)
			(19
				(ego normalize: 353 0)
				(Bclr 25)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance larryShowers of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset 65)
				(ego setMotion: PolyPath 228 99 self)
			)
			(1
				(ego
					view: 352
					setSpeed: 12
					loop: 0
					setCel: 0
					setCycle: End self
				)
			)
			(2
				(larryTowel init:)
				(ego loop: 1 setCel: 0 setCycle: CT 4 1 self)
			)
			(3
				(sFx2 number: 355 loop: 1 play:)
				(ego setCycle: End)
				(= ticks 30)
			)
			(4
				(sFx2 number: 355 loop: 1 play:)
				(ego setCel: 4 setCycle: End)
				(= ticks 30)
			)
			(5
				(sFx2 number: 355 loop: 1 play:)
				(ego setCel: 4 setCycle: End)
				(= ticks 30)
			)
			(6
				(if (Btst 25) (theGame changeScore: 3 192))
				(showerWater init: setPri: 199 setCycle: Fwd)
				(sFx2 number: 352 loop: -1 play:)
				(ego loop: 2 setCel: 0 setCycle: Fwd)
				(= seconds 5)
			)
			(7
				(ego loop: 3 setCel: 0 setCycle: End self)
			)
			(8
				(ego setCycle: CycleBet 3 4 5 self)
			)
			(9
				(ego loop: 1 setCel: 0 setCycle: End self)
			)
			(10
				(sFx2 number: 355 loop: 1 play:)
				(ego setCel: 4 setCycle: End)
				(= ticks 30)
			)
			(11
				(sFx2 number: 355 loop: 1 play:)
				(ego setCel: 4 setCycle: End)
				(= ticks 30)
			)
			(12
				(sFx2 number: 355 loop: 1 play:)
				(ego setCel: 4 setCycle: End)
				(= ticks 30)
			)
			(13
				(showerWater dispose:)
				(larryTowel dispose:)
				(ego loop: 4 setCel: 0 setCycle: End self)
			)
			(14
				(sFx2 stop:)
				(ego normalize: 353 0)
				(Bclr 25)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance fromHole of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(ego setSpeed: 12 setCycle: Beg self)
				(sFx number: 354 loop: 1 play:)
			)
			(2 (messager say: 1 0 5 0 self))
			(3
				(ego normalize: 900 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance lookThruHole of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 3 4 0 0 self)
			)
			(1
				(ego
					view: 355
					setSpeed: 12
					setLoop: 0
					setCel: 0
					setCycle: CT 3 1 self
				)
			)
			(2
				(ego setCycle: End self)
				(sFx number: 354 loop: 1 play:)
			)
			(3 (= ticks 120))
			(4 (curRoom newRoom: 360))
		)
	)
)

(instance gooseEgo of Script
	(properties)
	
	(method (changeState newState &tmp egoHeading)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= gEgoView (ego view?))
				(= egoHeading (ego heading?))
				(if (== gEgoView 900)
					(ego
						view: 354
						setSpeed: 2
						loop: (if (and (< egoHeading 226) (> egoHeading 89))
							0
						else
							1
						)
						setCel: 0
						setCycle: End self
					)
				else
					(ego
						view: 354
						setSpeed: 12
						loop: (if (and (< egoHeading 226) (> egoHeading 89))
							2
						else
							3
						)
						setCel: 0
						setCycle: End self
					)
				)
			)
			(1
				(sFx2 number: 357 loop: 1 play:)
				(if (== gEgoView 900)
					(ego setCycle: CycleBet 3 6 -1)
					(= seconds 2)
				else
					(self cue:)
				)
			)
			(2
				(if (== gEgoView 900)
					(ego setCycle: Beg self)
				else
					(= egoHeading (ego heading?))
					(ego
						loop: (if (and (< egoHeading 226) (> egoHeading 89))
							4
						else
							5
						)
						setCel: 0
						setCycle: End self
					)
				)
			)
			(3
				(= egoHeading (ego heading?))
				(ego
					normalize:
						gEgoView
						(if (and (< egoHeading 226) (> egoHeading 89))
							2
						else
							3
						)
				)
				(theGame handsOn:)
				(= seconds 3)
			)
			(4 (self dispose:))
		)
	)
)

(instance enterFromBath of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(mudBathDoor setCel: 7)
				(ego setMotion: MoveTo 173 86 self)
			)
			(1
				(mudBathDoor setCycle: Beg self)
			)
			(2
				(sFx number: 33 loop: 1 play: setVol: 127)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance enterFromLocker of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo 148 129 self)
			)
			(1
				(ego normalize:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance fountainScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (> local72 5)
					(self cue:)
				else
					(= seconds (Random 1 10))
				)
			)
			(1
				(if (Btst 49) (self init:) else (self cue:))
			)
			(2
				(= [local61 (self register?)] (Random 0 19))
				(if [local41 [local61 (self register?)]]
					(self init:)
				else
					(= [local41 [local61 (self register?)]] 1)
					(sFx number: 353 loop: 1 play:)
					(client
						x: [local1 [local61 (self register?)]]
						y: [local21 [local61 (self register?)]]
						loop: 1
						setCel: 0
						show:
						setCycle: End self
					)
				)
			)
			(3
				(client loop: 2 setCycle: End self)
			)
			(4
				(client loop: 5 setCel: 0 setCycle: End self)
			)
			(5
				(client hide:)
				(= [local41 [local61 (self register?)]] 0)
				(self init:)
			)
		)
	)
)

(instance showerGuy1 of Prop
	(properties
		noun 10
		x 78
		y 89
		view 358
		cel 1
	)
)

(instance showerGuy2 of Prop
	(properties
		noun 11
		x 107
		y 87
		view 359
		cel 2
	)
)

(instance water1 of Prop
	(properties
		x 65
		y 52
		view 358
		loop 2
		cel 3
	)
)

(instance water2 of Prop
	(properties
		x 107
		y 39
		view 359
		loop 2
		cel 1
	)
)

(instance towel1 of View
	(properties
		x 55
		y 61
		view 358
		loop 3
	)
)

(instance fountOJoy of Prop
	(properties
		view 350
		loop 1
	)
	
	(method (doit)
		(cond 
			((curRoom script?))
			(
				(and
					(!= (ego view?) 351)
					(== (self loop?) 2)
					(not (== (self signal?) 8))
					(not (Btst 49))
					(< (ego distanceTo: self) 5)
				)
				(curRoom setScript: gooseEgo)
			)
		)
		(super doit: &rest)
	)
)

(instance mudBathDoor of Prop
	(properties
		noun 2
		approachX 173
		approachY 85
		x 158
		y 76
		view 3500
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: toMudBath)
			)
			(1
				(++ local72)
				(super doVerb: theVerb)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance larryTowel of View
	(properties
		x 259
		y 68
		view 350
		loop 4
	)
)

(instance guy2Towel of View
	(properties
		x 132
		y 53
		view 359
		loop 3
	)
)

(instance showerWater of Prop
	(properties
		x 246
		y 55
		view 350
		loop 3
	)
)

(instance racks of PolyFeature
	(properties
		noun 9
	)
	
	(method (init)
		(super init: &rest)
		(self addPolygon: local82 local83 local84 local85 local86)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(56
					(showerHandle doVerb: 4)
					(return 1)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance nozzles of Feature
	(properties
		noun 4
	)
	
	(method (onMe theObjOrX)
		(if
			(or
				(local73 onMe: (theObjOrX x?) (theObjOrX y?))
				(local74 onMe: (theObjOrX x?) (theObjOrX y?))
				(local75 onMe: (theObjOrX x?) (theObjOrX y?))
				(local76 onMe: (theObjOrX x?) (theObjOrX y?))
				(local77 onMe: (theObjOrX x?) (theObjOrX y?))
			)
		else
			(local78 onMe: (theObjOrX x?) (theObjOrX y?))
		)
	)
)

(instance knobs of Feature
	(properties)
	
	(method (doVerb theVerb)
		(showerHandle doVerb: theVerb)
	)
	
	(method (onMe theObjOrX)
		(if
			(or
				(local79 onMe: (theObjOrX x?) (theObjOrX y?))
				(local80 onMe: (theObjOrX x?) (theObjOrX y?))
			)
		else
			(local81 onMe: (theObjOrX x?) (theObjOrX y?))
		)
	)
)

(instance showerHandle of Feature
	(properties
		noun 8
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 220 69 223 36 260 43 281 53 278 67 253 78
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(switch (ego view?)
					(353
						(curRoom setScript: larryShowers)
					)
					(351
						(curRoom setScript: larryShowersMud)
					)
					(else  (super doVerb: theVerb))
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance fountHandle of Feature
	(properties
		noun 6
		approachX 200
		approachY 90
		y 75
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 195 56 195 37 204 37 205 58 195 57
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(4
					(if (Btst 49)
						(Bclr 49)
						(messager say: 6 4 3)
						(return 1)
					else
						(Bset 49)
						(messager say: 6 4 2)
						(return 1)
					)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance looseTile of Feature
	(properties
		noun 3
		approachX 237
		approachY 106
		x 300
		y 100
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 253 86 258 78 262 89 256 95 253 87
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				((OneOf theVerb 4 5)
					(if (not (== (ego view?) 900))
						(messager say: 3 theVerb 1)
						(return 1)
					else
						(curRoom setScript: lookThruHole)
					)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance egoActions of Actions
	(properties)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(57
					(if (Btst 74)
						(messager say: 12 57 6)
						(return 1)
					else
						(messager say: 12 57 7)
						(return 1)
					)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance sFx of Sound
	(properties
		flags $0001
	)
)

(instance sFx2 of Sound
	(properties
		flags $0001
	)
)

(instance sFx3 of Sound
	(properties
		flags $0001
	)
)

(instance shoeFx of Sound
	(properties
		flags $0001
	)
)
