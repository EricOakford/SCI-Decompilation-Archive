;;; Sierra Script 1.0 - (do not remove this comment)
(script# 400)
(include sci.sh)
(use Main)
(use fileScr)
(use EgoDead)
(use LarryRoom)
(use CycleBet)
(use PolyPath)
(use Polygon)
(use Feature)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm400 0
)

(local
	local0
	local1
	local2
	local3
	gEgoView
	local5
)
(instance rm400 of LarryRoom
	(properties
		noun 1
		picture 400
		horizon 0
	)
	
	(method (init)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						2
						128
						0
						139
						156
						139
						168
						125
						184
						112
						205
						94
						257
						94
						276
						105
						284
						111
						242
						138
						319
						137
						319
						106
						309
						101
						308
						97
						287
						94
						284
						93
						257
						92
						207
						92
						192
						84
						170
						84
						164
						87
						133
						88
						122
						96
						105
						98
						81
						93
						58
						97
						92
						106
						62
						115
						29
						109
						1
						128
					yourself:
				)
				(= local0
					((Polygon new:)
						type: 2
						init:
							76
							115
							112
							101
							133
							97
							153
							99
							157
							101
							144
							110
							135
							117
							121
							123
							112
							131
							94
							132
							72
							127
							70
							123
						yourself:
					)
				)
				(= local1
					((Polygon new:)
						type: 2
						init:
							124
							127
							139
							117
							160
							102
							181
							100
							188
							105
							180
							112
							172
							118
							156
							135
							126
							135
						yourself:
					)
				)
		)
		(= local2
			((Polygon new:)
				type: 0
				init: 202 0 196 22 148 20 144 0
				yourself:
			)
		)
		(= local3
			((Polygon new:)
				type: 0
				init: 293 24 265 12 283 0 312 0 319 10 319 25 294 24
				yourself:
			)
		)
		(theMusic2 stop:)
		(ego init: normalize:)
		(manShowerDoor init: approachVerbs: 4)
		(plants init:)
		(if (not (Btst 203)) (lock init: approachVerbs: 4))
		(girlShowerDoor init: approachVerbs: 4)
		(steamDoor init: setPri: 80 approachVerbs: 4)
		(shockDoor
			init:
			setCel: (if (== prevRoomNum 380) 5 else 0)
			approachVerbs: 4
		)
		(leftGlass init: approachVerbs: 4)
		(rightGlass init: approachVerbs: 4)
		(newPlant init: approachVerbs: 4)
		(if (Btst 32)
			(plant1 posn: 42 108 loop: 0 cel: 0 init:)
			(plant2 posn: 44 111 loop: 1 cel: 0 init:)
			(plant3 posn: 54 113 loop: 2 cel: 0 init:)
		else
			(plant1 init: setPri: 107)
			(plant2 init: setPri: 107)
			(plant3 init: setPri: 107)
		)
		(camera
			init:
			setPri: 90
			setCel: (if (Btst 18) 3 else 0)
		)
		(bubbles1
			init:
			setPri: 30
			ignoreActors: 1
			setLoop: 4 1
			cycleSpeed: 8
			setCycle: Fwd
		)
		(bubbles2
			init:
			setPri: 30
			ignoreActors: 1
			setLoop: 5 1
			cycleSpeed: 10
			setCycle: Fwd
		)
		(couple1 init: setPri: 30 hide: setScript: couple1Script)
		(couple2 init: setPri: 30 hide: setScript: couple2Script)
		(bath1 init: approachVerbs: 4)
		(bath2 init:)
		(bath3 init:)
		(charBath init: approachVerbs: 4 1)
		(louver1 init:)
		(louver2 init:)
		(socket init:)
		(if (!= prevRoomNum 410)
			(theMusic number: 310 play: setVol: 127 setLoop: -1)
		)
		(if (and (Btst 31) (not (Btst 203)))
			(theCord init: approachVerbs: 5)
		)
		(if (not (Btst 88))
			(chardonnay
				init:
				approachVerbs: 1 2 4 25
				setScript: (if (== prevRoomNum 410) charDown else charScript)
			)
		)
		(super init: &rest)
		(switch prevRoomNum
			(350
				(self setScript: enterFromShower)
			)
			(370
				(if (== (ego view?) 900)
					(ego posn: 175 95 normalize: 900 3)
					(theGame handsOn:)
				else
					(self setScript: enterFromSteamRoom)
				)
			)
			(380
				(self setScript: enterFromShockRoom)
			)
			(420
				(Bset 9)
				(self setScript: enterFromWeightRoom)
			)
			(410
				(if (and (Btst 204) (Btst 203))
					(charScript dispose:)
					(curRoom setScript: charBails)
				else
					(theGame handsOn:)
				)
			)
			(else 
				(self setScript: enterFromShower)
			)
		)
		(bubbleFx number: 406 loop: -1 play: setVol: 127)
	)
	
	(method (doit &tmp egoEdgeHit)
		(= egoEdgeHit (ego edgeHit?))
		(super doit: &rest)
		(if
			(and
				(cast contains: theCord)
				(ego has: 9)
				(> (ego distanceTo: theCord) 20)
			)
			(ego put: 9)
		)
		(cond 
			(script)
			((== egoEdgeHit 2)
				(if (!= (ego view?) 900)
					(ego x: 317 setMotion: 0)
					(messager say: 1 0 2)
				else
					(curRoom setScript: toWeightRoom)
				)
			)
		)
	)
	
	(method (dispose)
		(local2 dispose:)
		(local3 dispose:)
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(2
					(if (cast contains: chardonnay)
						(messager say: 1 2 8)
						(return 1)
					else
						(super doVerb: theVerb)
					)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
	
	(method (cue)
		(self setScript: egoGetsFried)
	)
)

(instance tryToTurn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					normalize: 900
					setSpeed: 6
					setMotion: PolyPath 72 116 self
				)
			)
			(1 (ego setHeading: 310 self))
			(2 (= cycles 2))
			(3
				(ego
					setPri: 200
					setSpeed: 6
					setStep: 6 4
					setMotion: MoveTo 43 82 self
				)
			)
			(4
				(ego
					view: 403
					loop: 2
					setSpeed: 12
					setCel: 2
					x: 44
					y: 79
					setCycle: CT 3 1 self
				)
			)
			(5
				(ego setCycle: CycleBet 4 3 3 self)
			)
			(6 (ego setCycle: End self))
			(7
				(if (Btst 18)
					(messager say: 15 4 1 0 self)
				else
					(messager say: 15 4 0 0 self)
				)
			)
			(8
				(ego
					view: 900
					setSpeed: 6
					setCycle: Walk
					setMotion: MoveTo 78 113 self
				)
			)
			(9
				(ego normalize: 900)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance couple1Script of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 5 15)))
			(1
				(couple1 show: setCycle: Fwd)
				(bubbles1 cycleSpeed: 12 setLoop: 0 1 setCycle: Fwd)
				(= ticks 90)
			)
			(2
				(couple1 cycleSpeed: (Random 4 12))
				(bubbles1 cycleSpeed: (Random 4 12))
				(= ticks (Random 60 120))
			)
			(3
				(couple1 cycleSpeed: (Random 4 12))
				(bubbles1 cycleSpeed: (Random 4 12))
				(= ticks (Random 60 120))
			)
			(4
				(couple1 cycleSpeed: (Random 4 12))
				(bubbles1 cycleSpeed: (Random 4 12))
				(= ticks (Random 60 120))
			)
			(5
				(couple1 cycleSpeed: (Random 4 12))
				(bubbles1 cycleSpeed: (Random 4 12))
				(= ticks (Random 60 120))
			)
			(6
				(couple1 setCycle: 0 cycleSpeed: 12 hide:)
				(bubbles1 setCel: 0 setCycle: End self)
			)
			(7
				(bubbles1 setLoop: 4 1 cycleSpeed: 8 setCycle: Fwd)
				(self changeState: 0)
			)
		)
	)
)

(instance couple2Script of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(couple2 show: setCycle: Fwd)
				(bubbles2 cycleSpeed: 12 setLoop: 1 1 setCycle: Fwd)
				(= ticks 90)
			)
			(1
				(couple2 cycleSpeed: (Random 4 12))
				(bubbles2 cycleSpeed: (Random 4 12))
				(= ticks (Random 60 120))
			)
			(2
				(couple2 cycleSpeed: (Random 4 12))
				(bubbles2 cycleSpeed: (Random 4 12))
				(= ticks (Random 60 120))
			)
			(3
				(couple2 cycleSpeed: (Random 4 12))
				(bubbles2 cycleSpeed: (Random 4 12))
				(= ticks (Random 60 120))
			)
			(4
				(couple2 cycleSpeed: (Random 4 12))
				(bubbles2 cycleSpeed: (Random 4 12))
				(= ticks (Random 60 120))
			)
			(5
				(couple2 setCycle: 0 cycleSpeed: 12 hide:)
				(bubbles2 setCel: 0 setCycle: End self)
			)
			(6
				(bubbles2 setLoop: 5 1 cycleSpeed: 10 setCycle: Fwd)
				(= seconds (Random 5 15))
			)
			(7 (self changeState: 0))
		)
	)
)

(instance charDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(chardonnay setLoop: 3 setCel: 8 setCycle: Beg self)
			)
			(1
				(chardonnay setLoop: 0 setScript: charScript)
				(self dispose:)
			)
		)
	)
)

(instance muddyDeath of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Load rsVIEW 402)
				(ego setMotion: PolyPath 62 129 self)
			)
			(1 (ego setHeading: 90 self))
			(2 (= cycles 2))
			(3
				(messager say: 12 4 3 1 self)
			)
			(4
				(ego
					view: 402
					setSpeed: 12
					loop: 0
					setCel: 0
					setCycle: CT 8 1 self
				)
			)
			(5
				(sFx number: 401 loop: 1 play: setVol: 127)
				(ego setCycle: End self)
			)
			(6
				(ego loop: 1 setCycle: Fwd)
				(= cycles 2)
			)
			(7
				(messager say: 12 4 3 2 self)
			)
			(8 (= ticks 30))
			(9 (EgoDead 11 self))
			(10
				(ego normalize: 900 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance putPlantsUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setPri: -1 setMotion: PolyPath 48 114 self)
			)
			(1 (= cycles 2))
			(2
				(messager say: 11 0 13 0 self)
			)
			(3
				(ego
					view: 406
					setSpeed: 12
					loop: 0
					setCel: 9
					setCycle: CT 4 -1 self
				)
				(plant3 hide:)
			)
			(4 (= ticks 11))
			(5
				(ego setCel: 3 setCycle: Beg self)
				(plant3
					x: 66
					y: 102
					show:
					setPri: 107
					setLoop: 3
					setCel: 2
				)
			)
			(6
				(ego
					view: 900
					setCycle: Walk
					setSpeed: 6
					setMotion: PolyPath 40 113 self
				)
			)
			(7
				(plant2 hide:)
				(ego
					view: 406
					setLoop: 1
					setSpeed: 12
					setCel: 9
					setCycle: CT 4 -1 self
				)
			)
			(8 (= ticks 11))
			(9
				(ego cel: 3 setCycle: Beg self)
				(plant2 x: 56 y: 91 show: setPri: 107 loop: 3 setCel: 1)
			)
			(10
				(ego
					view: 900
					setSpeed: 6
					setCycle: Walk
					setMotion: PolyPath 34 109 self
				)
			)
			(11
				(plant1 hide:)
				(ego
					view: 406
					loop: 2
					setSpeed: 12
					setCel: 9
					setCycle: CT 4 -1 self
				)
			)
			(12 (= ticks 11))
			(13
				(ego cel: 3 setCycle: Beg self)
				(plant1 x: 49 y: 77 show: setPri: 107 loop: 3 setCel: 0)
			)
			(14
				(ego normalize: 900)
				(manShowerDoor setPri: -1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance toWeightRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo 330 (ego y?) self)
			)
			(1 (curRoom newRoom: 420))
		)
	)
)

(instance charBails of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(charScript dispose:)
				(chardonnay
					loop: 3
					setCel: 0
					cycleSpeed: 8
					setCycle: End self
				)
			)
			(1
				(proc79_3 ego chardonnay self)
			)
			(2 (= cycles 2))
			(3
				(messager say: 2 0 11 1 self)
			)
			(4
				(chardonnay
					view: 416
					setLoop: 0 1
					posn: 249 103
					cycleSpeed: 6
					setCycle: Walk
					ignoreActors: 1
					setMotion: MoveTo 130 95 self
				)
				(plant1 ignoreActors: 1)
				(plant2 ignoreActors: 1)
				(plant3 ignoreActors: 1)
			)
			(5
				(proc79_3 ego chardonnay self)
			)
			(6 (= cycles 2))
			(7
				(messager say: 2 0 11 2 self)
			)
			(8
				(chardonnay setLoop: 2 1 setMotion: MoveTo 53 125 self)
			)
			(9
				(chardonnay setLoop: 0 1 setMotion: MoveTo 22 116 self)
			)
			(10
				(sFx number: 32 loop: 1 play: setVol: 127)
				(girlShowerDoor setCycle: End self)
			)
			(11
				(chardonnay setMotion: MoveTo 16 107 self)
			)
			(12
				(chardonnay setPri: 0)
				(girlShowerDoor setCycle: Beg self)
			)
			(13
				(sFx number: 33 loop: 1 play: setVol: 127)
				(chardonnay dispose:)
				(ego setMotion: PolyPath 293 99 self)
			)
			(14
				(ego normalize: setMotion: MoveTo 302 96 self)
			)
			(15
				(ego
					view: 901
					loop: 6
					setCel: 0
					cycleSpeed: 6
					moveSpeed: 6
					setCycle: End self
				)
			)
			(16
				(sFx number: 32 loop: 1 play:)
				(shockDoor setCycle: End self)
			)
			(17
				(ego
					view: 900
					setLoop: 3
					setCycle: Fwd
					setMotion: MoveTo 297 90 self
				)
				(theMusic fade: 30 10 10 1 self)
			)
			(18 (ego setCycle: 0))
			(19
				(theMusic number: 0 stop:)
				(curRoom newRoom: 380)
			)
		)
	)
)

(instance egoGetsFried of Script
	(properties)
	
	(method (doit)
		(if (== (self state?) 1)
			(switch (ego cel?)
				(4 (sFx number: 403 play:))
				(9 (sFx number: 403 play:))
				(6 (sFx stop:))
				(11 (sFx stop:))
			)
		)
		(super doit: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 415
					loop: 0
					setSpeed: 12
					setCel: 0
					setCycle: End self
				)
			)
			(1
				(sFx number: 403 play:)
				(ego setCycle: CycleBet 4 12 2 self)
			)
			(2
				(sFx stop:)
				(ego loop: 1 setCel: 0 setCycle: End self)
			)
			(3 (EgoDead 6 self))
			(4
				(ego normalize: 900)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance enterFromShower of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(cond 
					((ResCheck 140 33) (= local5 140))
					((ResCheck 141 33) (= local5 141))
					(else (= local5 132))
				)
				(Load local5 33)
				(Lock local5 33 1)
				(ego posn: 68 91 normalize: (ego view?) 0)
				(manShowerDoor setCel: 8)
				(ego setPri: -1 setMotion: MoveTo 89 99 self)
			)
			(1
				(manShowerDoor setPri: -1 setCycle: Beg self)
			)
			(2
				(sFx number: 33 loop: 1 play: self setVol: 127)
				(Lock local5 33 0)
			)
			(3
				(UnLoad local5 33)
				(ego normalize:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance enterFromSteamRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego posn: 178 84)
				(sFx number: 32 loop: 1 play: setVol: 127)
				(steamDoor setCycle: End self)
			)
			(1
				(ego setPri: -1 setMotion: MoveTo 175 95 self)
			)
			(2
				(steamDoor setCycle: Beg self)
			)
			(3
				(sFx number: 33 loop: 1 play: setVol: 127)
				(ego normalize:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance enterFromShockRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					posn: 302 96
					normalize: (ego view?) 2
					setPri: -1
					setMotion: MoveTo 292 105 self
				)
			)
			(1
				(shockDoor setCycle: Beg self)
			)
			(2
				(sFx number: 33 loop: 1 play: setVol: 127)
				(ego normalize:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance enterFromWeightRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego posn: 325 100 setMotion: MoveTo 310 100 self)
			)
			(1
				(ego normalize:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance turnCamera of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 900
					setSpeed: 6
					setMotion: PolyPath 77 112 self
				)
			)
			(1
				(ego
					setPri: 200
					setStep: 6 4
					setMotion: MoveTo 48 79 self
				)
			)
			(2
				(ego
					view: 403
					loop: 0
					setSpeed: 12
					setCel: 0
					setCycle: End self
				)
			)
			(3
				(ego loop: 1 setCel: 0 setCycle: End self)
				(sFx number: 404 loop: 1 play:)
			)
			(4
				(sFx play:)
				(ego setCycle: Beg self)
			)
			(5
				(sFx play:)
				(ego setCycle: End self)
			)
			(6
				(sFx play:)
				(ego setCycle: Beg self)
			)
			(7
				(sFx play:)
				(ego setCycle: End self)
			)
			(8
				(sFx play:)
				(ego setCycle: Beg self)
			)
			(9
				(sFx play:)
				(ego setCycle: End self)
			)
			(10
				(ego loop: 2 setCel: 0 setCycle: CT 5 1 self)
			)
			(11
				(ego setCycle: End self)
				(sFx number: 405 play:)
				(if (Btst 18)
					(camera setCycle: Beg self)
				else
					(camera setCycle: End self)
				)
			)
			(12)
			(13
				(if (Btst 18)
					(Bclr 18)
					(messager say: 15 64 1 0 self)
				else
					(Bset 18)
					(theGame changeScore: 12 200)
					(messager say: 15 64 0 0 self)
				)
			)
			(14
				(ego loop: 1 setCel: 0 setCycle: End self)
				(sFx number: 404 loop: 1 play:)
			)
			(15
				(sFx play:)
				(ego setCycle: Beg self)
			)
			(16
				(sFx play:)
				(ego setCycle: End self)
			)
			(17
				(sFx play:)
				(ego setCycle: Beg self)
			)
			(18
				(sFx play:)
				(ego setCycle: End self)
			)
			(19
				(sFx play:)
				(ego setCycle: Beg self)
			)
			(20
				(sFx play:)
				(ego setCycle: End self)
			)
			(21
				(ego
					view: 900
					setSpeed: 6
					setCycle: Walk
					setMotion: MoveTo 78 113 self
				)
			)
			(22
				(ego normalize: 900)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance toMensShower of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= gEgoView (ego view?))
				(cond 
					((== (ego view?) 900)
						(ego
							view: 901
							loop: 1
							setCel: 0
							setSpeed: 12
							setCycle: CT 2 1 self
						)
					)
					((OneOf (ego view?) 353 351)
						(ego
							view: 356
							loop: (if (== (ego view?) 353) 1 else 0)
							setCel: 0
							setSpeed: 12
							setCycle: CT 2 1 self
						)
					)
					(else (self cue:))
				)
			)
			(1
				(sFx number: 32 loop: 1 play: setVol: 127)
				(ego setCycle: End self)
				(manShowerDoor setCycle: End self)
			)
			(2)
			(3
				(ego
					view: gEgoView
					setCycle: Walk
					setPri: (- (manShowerDoor priority?) 1)
					setSpeed: 6
					setMotion: MoveTo 65 92 self
				)
			)
			(4
				(theMusic fade:)
				(curRoom newRoom: 350)
			)
		)
	)
)

(instance toSteamRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= gEgoView (ego view?))
				(cond 
					((== (ego view?) 900)
						(ego
							view: 901
							loop: 6
							setCel: 0
							setSpeed: 12
							setCycle: End self
						)
					)
					((== (ego view?) 353)
						(ego
							view: 356
							loop: 2
							setCel: 0
							cycleSpeed: 12
							setCycle: End self
						)
					)
					(else (self cue:))
				)
			)
			(1
				(sFx number: 32 loop: 1 play: setVol: 127)
				(steamDoor setCycle: End self)
			)
			(2
				(ego
					view: gEgoView
					setCycle: Walk
					setSpeed: 6
					setMotion: MoveTo 178 83 self
				)
			)
			(3 (curRoom newRoom: 370))
		)
	)
)

(instance toShockRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= gEgoView (ego view?))
				(if (== (ego view?) 900)
					(ego
						view: 901
						loop: 6
						setCel: 0
						setSpeed: 12
						setCycle: End self
					)
				else
					(self cue:)
				)
			)
			(1
				(sFx number: 32 loop: 1 play: setVol: 127)
				(shockDoor setCycle: End self)
			)
			(2
				(ego
					view: gEgoView
					setCycle: Walk
					setMotion: MoveTo 302 96 self
				)
			)
			(3 (curRoom newRoom: 380))
		)
	)
)

(instance jumpInMud of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 62 129 self)
			)
			(1
				(ego
					view: 404
					setSpeed: 12
					loop: 0
					setCel: 0
					setCycle: CT 8 1 self
				)
			)
			(2
				(larryTowel init: ignoreActors: 1)
				(ego setCycle: End self)
				(sFx number: 401 loop: 1 play:)
			)
			(3
				(ego loop: 1 setCel: 0 setCycle: End self)
			)
			(4
				(theGame changeScore: 1 201)
				((ScriptID 0 11) init: self)
				(ego state: (& (ego state?) $fffd))
				(theGame handsOn:)
				(theIconBar
					disableIcon:
						(ScriptID 0 7)
						(ScriptID 0 5)
						(ScriptID 0 9)
						(ScriptID 0 6)
					show:
				)
			)
			(5
				((ScriptID 0 11) dispose:)
				(theGame handsOff:)
				(ego
					loop: 2
					setCel: 0
					state: (| $0002 (ego state?))
					setCycle: End self
				)
				(sFx number: 402 play:)
			)
			(6
				(messager say: 12 0 10 0 self)
			)
			(7 (= seconds 3))
			(8
				(ego
					x: 62
					y: 126
					setLoop: 3
					setCel: 0
					setCycle: CT 9 1 self
				)
			)
			(9
				(larryTowel dispose:)
				(ego setCycle: End self)
			)
			(10
				(Bset 25)
				(ego posn: 75 128 normalize: 351 1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance plugInCord of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Load rsVIEW 405)
				(ego setMotion: PolyPath 259 96 self)
			)
			(1
				(ego setMotion: MoveTo 259 96 self)
			)
			(2
				(ego
					view: 405
					setSpeed: 12
					loop: (if register 1 else 0)
					setCel: 0
					setCycle: End self
				)
			)
			(3
				(theGame changeScore: 7 202)
				(Bset 31)
				(theCord
					init:
					approachVerbs: 5
					cel: (if register 1 else 0)
				)
				(ego normalize: 900 0 setMotion: MoveTo 260 95 self)
			)
			(4
				(ego normalize: 900 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance openShockDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 275 93 self)
			)
			(1
				(ego
					view: 413
					setSpeed: 12
					loop: 0
					setCel: 0
					setCycle: End self
				)
				(theCord dispose:)
			)
			(2
				(sFx number: 403 loop: 1 play:)
				(ego view: 414 loop: 0 setCel: 0 setCycle: End self)
			)
			(3
				(sFx stop:)
				(ego loop: 1 setCel: 0 setCycle: End self)
			)
			(4
				(messager sayRange: 6 27 9 1 2 self)
			)
			(5
				(sFx number: 572 loop: 1 play: setVol: 127)
				(if (Btst 204)
					(proc79_3 ego chardonnay self)
				else
					(self cue:)
				)
			)
			(6 (= cycles 2))
			(7
				(if (not (Btst 204))
					(messager say: 6 27 9 3 self)
				else
					(messager sayRange: 6 27 9 4 7 self)
				)
			)
			(8
				(theGame changeScore: 12 203)
				(ego normalize: 900 0 put: 9)
				(if (Btst 204)
					(curRoom setScript: charBails)
				else
					(theGame handsOn:)
					(self dispose:)
				)
			)
		)
	)
)

(instance touchPad of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 274 94 self)
			)
			(1
				(ego
					view: 413
					setSpeed: 12
					loop: 0
					setCel: 0
					setCycle: End self
				)
				(theCord dispose:)
			)
			(2
				(messager say: 6 26 9 0 self)
			)
			(3 (ego setCycle: Beg self))
			(4
				(theCord init:)
				(ego normalize: 900)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance checkOutChar of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(proc79_3 ego chardonnay self)
			)
			(1 (= cycles 2))
			(2
				(charScript dispose:)
				(if (Btst 64)
					(messager say: 19 1 8 0 self)
				else
					(messager say: 19 1 14 0 self)
				)
			)
			(3
				(chardonnay loop: 3 setCel: 0 setCycle: CT 8 1 self)
			)
			(4 (curRoom newRoom: 410))
		)
	)
)

(instance charScript of Script
	(properties)
	
	(method (dispose)
		(chardonnay setCycle: 0)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 1 10)))
			(1
				(chardonnay
					loop: 2
					setCel: 4
					cycleSpeed: 12
					setCycle: Beg self
				)
			)
			(2
				(chardonnay loop: 1 setCycle: Fwd)
				(= seconds (Random 1 5))
			)
			(3
				(chardonnay loop: 2 setCel: 0 setCycle: End self)
			)
			(4
				(chardonnay setCycle: 0 loop: 0 setCel: 0)
				(self changeState: 0)
			)
		)
	)
)

(instance chardonnay of Actor
	(properties
		approachX 268
		approachY 133
		x 258
		y 110
		view 401
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			((OneOf theVerb 2 1 4)
				(if (curRoom script?)
					(super doVerb: theVerb)
				else
					(curRoom setScript: checkOutChar)
				)
			)
			(25
				(Bset 39)
				(curRoom setScript: checkOutChar)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance couple1 of Prop
	(properties
		noun 3
		x 108
		y 19
		view 411
		loop 2
		cycleSpeed 8
	)
)

(instance couple2 of Prop
	(properties
		noun 3
		x 239
		y 19
		view 411
		loop 3
		cel 7
		cycleSpeed 8
	)
)

(instance bubbles1 of Prop
	(properties
		noun 3
		x 107
		y 19
		view 411
		cel 2
		cycleSpeed 8
	)
)

(instance bubbles2 of Prop
	(properties
		noun 3
		x 239
		y 19
		view 411
		loop 1
		cel 4
		cycleSpeed 8
	)
)

(instance plants of Feature
	(properties
		noun 8
	)
	
	(method (onMe theObjOrX)
		(if (local2 onMe: (theObjOrX x?) (theObjOrX y?))
		else
			(local3 onMe: (theObjOrX x?) (theObjOrX y?))
		)
	)
)

(instance louver1 of Feature
	(properties
		noun 20
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 3 50 6 43 14 37 23 37 31 45 31 49
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance louver2 of Feature
	(properties
		noun 21
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 48 43 56 30 66 27 77 34 78 38 49 43
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance plant1 of View
	(properties
		noun 8
		x 49
		y 77
		view 407
		loop 3
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(4
					(cond 
						((!= (ego view?) 900) (messager say: 1 0 5) (return 1))
						((Btst 32) (Bclr 32) (curRoom setScript: putPlantsUp))
						(else (Bset 32) (curRoom setScript: takePlantsDown))
					)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance plant2 of View
	(properties
		noun 8
		x 56
		y 91
		view 407
		loop 3
		cel 1
	)
	
	(method (doVerb theVerb)
		(plant1 doVerb: theVerb)
	)
)

(instance plant3 of View
	(properties
		noun 8
		x 66
		y 102
		view 407
		loop 3
		cel 2
	)
	
	(method (doVerb theVerb)
		(plant1 doVerb: theVerb)
	)
)

(instance theCord of View
	(properties
		noun 13
		approachX 269
		approachY 86
		x 270
		y 83
		view 412
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(5
					(cond 
						((!= (ego view?) 900) (messager say: 1 0 5) (return 1))
						((not (ego has: 9)) (ego get: 9) (return 1))
						(else (theCord dispose:) (Bclr 31) (super doVerb: theVerb))
					)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance larryTowel of View
	(properties
		x 62
		y 129
		view 404
		loop 4
	)
)

(instance girlShowerDoor of Prop
	(properties
		noun 4
		approachX 26
		approachY 125
		x 2
		y 60
		view 400
		loop 2
	)
)

(instance manShowerDoor of Prop
	(properties
		noun 5
		approachX 80
		approachY 94
		x 79
		y 90
		view 400
		loop 3
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(curRoom setScript: toMensShower)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance camera of Prop
	(properties
		noun 15
		x 37
		y 25
		view 400
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(64
					(cond 
						((!= (ego view?) 900) (messager say: 1 0 5) (return 1))
						((Btst 32) (curRoom setScript: turnCamera))
						(else (messager say: 15 4 12) (return 1))
					)
				)
				(4
					(cond 
						((!= (ego view?) 900) (messager say: 1 0 5) (return 1))
						((Btst 32) (curRoom setScript: tryToTurn))
						(else (messager say: 15 4 12) (return 1))
					)
				)
				(1
					(if (Btst 18)
						(messager say: 15 1 1)
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

(instance steamDoor of Prop
	(properties
		noun 7
		approachX 176
		approachY 92
		x 161
		y 84
		view 400
		loop 4
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(if (== (ego view?) 351)
				(messager say: 7 4 4)
			else
				(curRoom setScript: toSteamRoom)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance shockDoor of Prop
	(properties
		noun 6
		approachX 293
		approachY 99
		x 282
		y 92
		view 400
		loop 5
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(4
					(cond 
						((not (Btst 203)) (messager say: 6 4 6))
						((== (ego view?) 351) (messager say: 6 4 4))
						(else (curRoom setScript: toShockRoom))
					)
				)
				(26
					(cond 
						((!= (ego view?) 900) (messager say: 6 26 2) (return 1))
						((Btst 31) (curRoom setScript: touchPad))
						(else (messager say: 6 26 7) (return 1))
					)
				)
				(27
					(cond 
						((!= (ego view?) 900) (messager say: 6 27 2) (return 1))
						((Btst 31) (curRoom setScript: openShockDoor))
						(else (messager say: 6 27 7 0 self))
					)
				)
				(1
					(if (not (Btst 203))
						(messager say: 6 1 6)
						(return 1)
					else
						(super doVerb: theVerb)
					)
				)
				(2
					(if (not (Btst 203))
						(messager say: 6 2 6)
						(return 1)
					else
						(super doVerb: theVerb)
					)
				)
				(6
					(if (not (Btst 203))
						(messager say: 6 6 6)
					else
						(super doVerb: theVerb)
					)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance socket of Feature
	(properties
		noun 9
		nsLeft 260
		nsTop 68
		nsRight 276
		nsBottom 89
		approachX 267
		approachY 94
		x 268
		y 78
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(4
					(if (and (not (Btst 203)) (Btst 31))
						(ego get: 9)
						(Bclr 31)
					else
						(super doVerb: theVerb)
					)
				)
				(26
					(if (!= (ego view?) 900)
						(messager say: 1 0 5)
						(return 1)
					else
						(curRoom setScript: plugInCord 0 0)
					)
				)
				(27
					(if (!= (ego view?) 900)
						(messager say: 1 0 5)
						(return 1)
					else
						(curRoom setScript: plugInCord 0 1)
					)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance leftGlass of Feature
	(properties
		noun 3
		approachX 108
		approachY 100
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 122 43 102 44 89 38 79 30 77 16 91 13 143 12 145 22 139 33
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance rightGlass of Feature
	(properties
		noun 3
		approachX 232
		approachY 94
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 201 15 266 15 270 18 263 32 246 45 228 45 217 41 206 32
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance bath1 of Feature
	(properties
		noun 12
		approachX 62
		approachY 129
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(4
					(cond 
						((== (ego view?) 353) (curRoom setScript: jumpInMud))
						((== (ego view?) 900) (curRoom setScript: muddyDeath))
						(else (messager say: 12 4 4) (return 1))
					)
				)
				(1
					(messager say: 12 1 0 3)
					(return 1)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
	
	(method (onMe theObjOrX)
		(local0 onMe: (theObjOrX x?) (theObjOrX y?))
	)
)

(instance bath2 of Feature
	(properties
		noun 12
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(4 (bath1 doVerb: theVerb))
				(1
					(messager say: 12 1 0 2)
					(return 1)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
	
	(method (onMe theObjOrX)
		(local1 onMe: (theObjOrX x?) (theObjOrX y?))
	)
)

(instance bath3 of Feature
	(properties
		noun 12
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						166
						136
						173
						130
						180
						122
						209
						109
						225
						108
						216
						113
						213
						121
						209
						128
						195
						132
						183
						138
						169
						138
						166
						137
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(4 (bath1 doVerb: theVerb))
				(1
					(messager say: 12 1 0 1)
					(return 1)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance charBath of Feature
	(properties
		noun 19
		approachX 268
		approachY 128
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 200 138 201 135 247 106 276 111 238 138 201 138
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if
					(and
						(not (curRoom script?))
						(cast contains: chardonnay)
					)
					(curRoom setScript: checkOutChar)
				else
					(bath2 doVerb: theVerb)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance newPlant of Feature
	(properties
		noun 8
		approachX 232
		approachY 94
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 206 62 212 47 229 45 245 48 251 63 245 74 233 82 225 82
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance lock of Feature
	(properties
		noun 10
		approachX 293
		approachY 99
		y 91
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 293 66 302 66 301 76 293 75
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(super doVerb: theVerb)
		else
			(shockDoor doVerb: theVerb)
		)
	)
)

(instance sFx of Sound
	(properties)
)

(instance bubbleFx of Sound
	(properties)
)

(instance takePlantsDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Load rsVIEW 406)
				(ego setMotion: PolyPath 34 109 self)
			)
			(1
				(ego
					setSpeed: 12
					view: 406
					loop: 2
					setCel: 0
					setCycle: CT 3 1 self
				)
			)
			(2 (= ticks 11))
			(3
				(ego cel: 4 setCycle: End self)
				(plant1 hide:)
			)
			(4
				(plant1
					ignoreActors: 1
					show:
					posn: 41 108
					loop: 0
					setCel: 0
				)
				(ego
					normalize: 900
					setSpeed: 6
					setMotion: PolyPath 40 113 self
				)
			)
			(5
				(ego
					view: 406
					setSpeed: 12
					loop: 1
					setCel: 0
					setCycle: CT 3 1 self
				)
			)
			(6 (= ticks 11))
			(7
				(plant2 hide:)
				(ego cel: 4 setCycle: End self)
			)
			(8
				(plant2
					ignoreActors: 1
					show:
					posn: 44 110
					loop: 1
					setCel: 0
				)
				(ego
					normalize: 900
					setSpeed: 6
					setMotion: PolyPath 48 114 self
				)
			)
			(9
				(ego
					view: 406
					setSpeed: 12
					loop: 0
					setCel: 0
					setCycle: CT 3 1 self
				)
			)
			(10 (= ticks 11))
			(11
				(plant3 hide:)
				(ego cel: 4 setCycle: End self)
			)
			(12
				(plant3
					ignoreActors: 1
					show:
					posn: 54 112
					loop: 2
					setCel: 0
				)
				(theGame changeScore: 5 199)
				(ego normalize: 900 setMotion: PolyPath 62 123 self)
				(manShowerDoor setPri: -1)
			)
			(13
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)
