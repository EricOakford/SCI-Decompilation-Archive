;;; Sierra Script 1.0 - (do not remove this comment)
(script# 510)
(include sci.sh)
(use Main)
(use fileScr)
(use EgoDead)
(use LarryRoom)
(use PolyFeature)
(use Print)
(use Inset)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use DPath)
(use Timer)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm510 0
	bartender 1
	burgundy 2
)

(local
	local0
	local1
	gGLarryRoomObstacles
	local3 =  1
	local4
	local5 =  6
	local6
	local7 =  -1
	local8
	local9
	local10
	local11
)
(instance rm510 of LarryRoom
	(properties
		noun 1
		picture 510
		horizon 0
		east 505
		south 505
		autoLoad 0
	)
	
	(method (init)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						245
						138
						319
						139
						319
						105
						267
						102
						226
						92
						226
						83
						287
						78
						224
						54
						108
						56
						65
						44
						38
						46
						0
						50
						0
						69
						38
						69
						59
						84
						70
						92
						97
						97
						119
						94
						146
						87
						184
						86
						231
						100
						245
						114
						245
						125
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 139 63 151 62 227 68 229 76 210 84 139 78
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 266 113 319 112 319 131 269 130
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 39 62 108 62 108 77 60 79 40 66
					yourself:
				)
		)
		(super init: &rest)
		(if (!= prevRoomNum 520)
			(Bclr 37)
			(= global172 1)
			(= global181 0)
		)
		(cond 
			((OneOf global181 2 1) (Bclr 37))
			((and (== global181 3) (!= global172 28)) (garyTimer setReal: gary 5))
		)
		(ego
			normalize: 900 4
			ignoreActors: 0
			setScaler: Scaler 100 84 94 43
			init:
		)
		(bottles init:)
		(palmTrees init:)
		(tank init:)
		(mikeStand init:)
		(phallicFruit init:)
		(matchDish init: approachVerbs: 4 1 2 5 6 -1)
		(barDoor init:)
		(bartender
			init:
			ignoreActors:
			approachVerbs: 2
			setCycle: Walk
			setScript: bartenderScr
		)
		(switch prevRoomNum
			(520
				(ego posn: (burgundy approachX?) (burgundy approachY?))
				(theGame handsOn:)
			)
			(else 
				(self setScript: from3DoorHallScr)
			)
		)
		(if (theMusic2 handle?) (theMusic2 stop:))
		(cord init: approachVerbs: 1 4)
		(cordFtr init: approachVerbs: 1 4)
		(if (Btst 3)
			(if (not (Btst 215))
				(burgundy init: approachVerbs: 2 1 9)
				(if (== prevRoomNum 520)
					(if (== global181 1)
						(gary
							init:
							posn: 175 115
							loop: 4
							setPri: 130
							setScript: gary2Scr 0 (if (Btst 98) 17 else 16)
						)
					)
					(cond 
						((== global172 28) (burgundy setScript: giveBeerScr))
						((!= global181 2) (= global172 14))
						(else (burgundy setScript: backToGuitarScr))
					)
				)
				(cond 
					((== global172 1)
						(if (> (Random 0 100) 50)
							(theMusic number: 1001 loop: 1 play: songManager)
						else
							(theMusic number: 515 loop: -1 play:)
						)
						(burgundy setCycle: Fwd)
					)
					((!= prevRoomNum 520) (theMusic2 number: 200 loop: -1 play:))
				)
				(guitarStand init:)
			else
				(theMusic
					number: (if (Random 0 1) 511 else 513)
					setLoop: -1
					play:
				)
				(if (>= (theGame detailLevel:) 2)
					(syncSwimmer2 init:)
					(syncSwimmer1 init: setScript: syncSwimmerScr)
				)
			)
		else
			(theMusic
				number: (if (Random 0 1) 511 else 513)
				setLoop: -1
				play:
			)
			(if (>= (theGame detailLevel:) 2)
				(waitress init: hide: setScript: waitressScr)
			)
			(Bset 3)
		)
		(tray init:)
		(stage init:)
		(if (>= (theGame detailLevel:) 3)
			(fish1 init: hide: setScript: fishScr)
			(fish2 init: hide:)
			(fish3 init: hide:)
		)
		(if (not (cast contains: burgundy))
			(walkHandler add: stage)
		)
	)
	
	(method (doit)
		(super doit: &rest)
		(cond 
			((or (not local0) script) 0)
			((ego inRect: 25 75 76 90) (self setScript: fromStageScr))
			((< (ego y?) 112)
				(if
				(or (not (== local1 1)) (!= (ego priority?) 117))
					(ego setPri: 117)
					(= local1 1)
				)
			)
			(
			(or (not (== local1 0)) (!= (ego priority?) 130)) (ego setPri: 130) (= local1 0))
		)
	)
	
	(method (dispose)
		(Bclr 86)
		(theMusic flags: 1)
		(walkHandler delete: stage self)
		(theMusic stop:)
		(if (theMusic2 handle?) (theMusic2 stop:))
		(garyTimer dispose: delete:)
		(burgWhineTimer dispose: delete:)
		(if (!= gGLarryRoomObstacles 0)
			(gGLarryRoomObstacles eachElementDo: #dispose)
			(gGLarryRoomObstacles eachElementDo: #delete)
			(gGLarryRoomObstacles dispose:)
		)
		(DisposeScript -572)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) 0)
			((== (event message?) JOY_RIGHT)
				(if
				(and (not (stage onMe: event)) (< (event y?) 139))
					(self doVerb: 3)
					(event claimed: 1)
				)
			)
			(else (super handleEvent: event))
		)
		(event claimed?)
	)
	
	(method (doVerb theVerb)
		(cond 
			((and (== theVerb 1) local0) (messager say: noun theVerb 11))
			((== theVerb 3) (ego setMotion: PolyPath 42 80))
			(else (return (super doVerb: theVerb)))
		)
		(return 1)
	)
	
	(method (cue &tmp theInset)
		(if inset
			(= theInset inset)
			(inset dispose:)
			(if (== theInset dressInset)
				(self setScript: fromBSInset)
			)
		else
			(= gGButtonBarGetCursor theCursor)
			(theGame setCursor: normalCursor)
			(SetCursor 200 70)
			(if
				(==
					(Print
						width: 150
						font: userFont
						addTitle: 16 0 25 1 510
						addText: 16 0 0 1 50 0
						addButton: 100 16 0 27 1 160 33
						addButton: 200 16 0 26 1 50 33
						x: 30
						y: 20
						addIcon: 1911 0 0 0 0
						init:
					)
					100
				)
				(ego posn: (burgundy approachX?) (burgundy approachY?))
				(if global205 (proc79_7))
				(burgundy dispose:)
				(if (burgSFX handle?) (burgSFX stop:))
				((inventory at: 17) owner: 510)
				(walkHandler add: stage)
				(theIconBar disableIcon: (ScriptID 0 8) show:)
				(theGame handsOn:)
			else
				(= gLarryRoom self)
				(theIconBar enableIcon: (ScriptID 0 8) show:)
			)
			(theGame setCursor: gGButtonBarGetCursor)
		)
	)
	
	(method (edgeToRoom param1)
		(return
			(cond 
				((and (== param1 3) local0) (self setScript: toBSInset) (return 0))
				((OneOf param1 2 3) (self setScript: toHallScr 0 param1) (return 0))
				(else (super edgeToRoom: param1))
			)
		)
	)
)

(instance toBSInset of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					setMotion: MoveTo (- (ego x?) 18) (+ (ego y?) 45) self
				)
			)
			(1
				(curRoom setInset: dressInset)
				(ego edgeHit: 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance fromBSInset of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					setMotion: MoveTo (+ (ego x?) 18) (- (ego y?) 48) self
				)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance dressInset of Inset
	(properties
		picture -2
		view 514
		x 110
		y 24
		noun 13
	)
	
	(method (init)
		(super init: &rest)
		(gGraphMenuBar state: (| (gGraphMenuBar state?) $0004))
		(if (== ((inventory at: 17) owner?) 510)
			(insetDress init:)
		)
		(Bset 86)
		((ScriptID 0 11) init: curRoom)
		(plane setSize:)
		(UpdatePlane plane)
		(FrameOut)
	)
	
	(method (dispose)
		(Bclr 86)
		(gGraphMenuBar state: (& (gGraphMenuBar state?) $fffb))
		((ScriptID 0 11) dispose:)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(cond 
			((OneOf theVerb 1 5)
				(if
				(and (== theVerb 5) (cast contains: insetDress))
					(insetDress doVerb: theVerb)
				else
					(messager
						say: noun theVerb (if (not (cast contains: insetDress)) 13 else 0)
					)
				)
			)
			((OneOf theVerb 6 2 4) (super doVerb: theVerb))
			(else (messager say: noun 0 0))
		)
	)
)

(instance bottles of PolyFeature
	(properties
		noun 4
		sightAngle 40
		y 34
		variableX 1
	)
	
	(method (init)
		(super init: &rest)
		(self
			addPolygon:
				((Polygon new:)
					init: 121 31 121 18 133 18 139 22 139 33
					yourself:
				)
		)
	)
)

(instance insetDress of View
	(properties
		noun 14
		x 51
		y 35
		priority 200
		fixPriority 1
		view 514
		loop 1
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 5)
			((curRoom inset?) setScript: takeGownScr)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance mikeStand of Feature
	(properties
		noun 8
		sightAngle 40
		approachX 175
		approachY 114
		x 175
		y 108
	)
	
	(method (init)
		(super init: &rest)
		(self
			setPolygon:
				((Polygon new:)
					init: 179 76 180 98 188 107 178 116 163 109 171 98 171 77
					yourself:
				)
		)
	)
	
	(method (doVerb)
		(messager say: noun 0 (if local0 11 else 0))
	)
	
	(method (onMe theObjOrX)
		(return
			(if (super onMe: theObjOrX)
				(if local0
					(self approachVerbs: 4 1 2 5 6)
				else
					(self approachVerbs:)
				)
				1
			else
				0
			)
		)
	)
)

(instance barDoor of Prop
	(properties
		sightAngle 40
		approachX 53
		approachY 45
		x 65
		y 7
		view 510
		cycleSpeed 8
	)
	
	(method (init)
		(self setPri: 44)
		(super init: &rest)
		(self approachVerbs: 4)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: enterPoolScr)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (onMe theObjOrX)
		(return
			(if (super onMe: theObjOrX)
				(if
					(and
						local0
						(&
							(approachCode doit: (theObjOrX message?))
							_approachVerbs
						)
					)
					(ego setMotion: PolyPath 42 80)
					(= local6 ((ego looper?) oldMover?))
					(CueObj client: self theVerb: (theObjOrX message?))
					(theObjOrX claimed: 1)
					(return 0)
				else
					(return 1)
				)
			else
				(return 0)
			)
		)
	)
)

(instance syncSwimmer1 of Prop
	(properties
		noun 17
		view 511
		cycleSpeed 11
	)
)

(instance syncSwimmer2 of Prop
	(properties
		noun 17
		view 511
		cycleSpeed 11
	)
)

(instance burgundy of Actor
	(properties
		noun 5
		sightAngle 40
		approachX 143
		approachY 87
		x 175
		y 117
		priority 130
		fixPriority 1
		view 515
		cycleSpeed 10
	)
	
	(method (init)
		(if (OneOf global172 28 14)
			(burgundy
				setPri: 117
				view: 516
				setLoop: 0
				setCel: 8
				posn: 157 98 10
			)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(9
				(if (== global172 14)
					(if (timers contains: burgWhineTimer)
						(burgWhineTimer dispose:)
					)
					(= global173 1)
					(self setScript: goToInsetScr 0 1)
				else
					(super doVerb: theVerb)
				)
			)
			(1
				(cond 
					((== global172 14) (self setScript: goToInsetScr))
					((and (Btst 46) (== global172 1)) (messager say: noun theVerb 24))
					(else (messager say: noun theVerb global172))
				)
			)
			(2
				(if (== global172 14)
					(self setScript: goToInsetScr)
				else
					(messager say: noun theVerb global172)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (cue)
		(if
			(and
				(not (curRoom inset?))
				(prints isEmpty:)
				(talkers isEmpty:)
			)
			(messager say: noun 0 (if local9 16 else 17) 4)
		else
			(burgWhineTimer setReal: self 10)
		)
	)
)

(instance matchDish of View
	(properties
		noun 10
		sightAngle 40
		approachX 84
		approachY 53
		x 86
		y 35
		priority 49
		fixPriority 1
		view 510
		loop 2
	)
	
	(method (doVerb)
		(curRoom setInset: matchInset)
	)
	
	(method (onMe theObjOrX)
		(return
			(if (super onMe: theObjOrX)
				(if
					(and
						local0
						(&
							(approachCode doit: (theObjOrX message?))
							_approachVerbs
						)
					)
					(ego setMotion: PolyPath 42 80)
					(= local6 ((ego looper?) oldMover?))
					(CueObj client: self theVerb: (theObjOrX message?))
					(theObjOrX claimed: 1)
					(return 0)
				else
					(return 1)
				)
			else
				(return 0)
			)
		)
	)
)

(instance cord of View
	(properties
		sightAngle 40
		approachX 251
		approachY 126
		x 226
		y 117
		view 510
		loop 4
	)
	
	(method (init)
		(super init: &rest)
		(= cel (if (Btst 37) 1 else 0))
	)
	
	(method (doVerb theVerb)
		(cordFtr doVerb: theVerb)
	)
	
	(method (onMe theObjOrX)
		(return
			(if (super onMe: theObjOrX)
				(if
					(and
						local0
						(&
							(approachCode doit: (theObjOrX message?))
							_approachVerbs
						)
					)
					(ego setMotion: PolyPath 42 80)
					(= local6 ((ego looper?) oldMover?))
					(CueObj client: self theVerb: (theObjOrX message?))
					(theObjOrX claimed: 1)
					(return 0)
				else
					(return 1)
				)
			else
				(return 0)
			)
		)
	)
)

(instance cordFtr of Feature
	(properties
		noun 11
		sightAngle 40
		approachX 251
		approachY 126
		x 226
		y 117
	)
	
	(method (init)
		(super init: &rest)
		(self
			setPolygon:
				((Polygon new:)
					init: 203 106 214 113 243 113 244 121 210 121 201 116 186 112 190 105
					yourself:
				)
		)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(cond 
				((or (cast contains: gary) (burgundy script?)) (messager say: noun theVerb 20))
				((Bset 37) (messager say: noun theVerb 2))
				(else (curRoom setScript: unplugCordScr))
			)
		else
			(super doVerb: theVerb)
		)
	)
	
	(method (onMe theObjOrX)
		(return
			(if (super onMe: theObjOrX)
				(if
					(and
						local0
						(&
							(approachCode doit: (theObjOrX message?))
							_approachVerbs
						)
					)
					(ego setMotion: PolyPath 42 80)
					(= local6 ((ego looper?) oldMover?))
					(CueObj client: self theVerb: (theObjOrX message?))
					(theObjOrX claimed: 1)
					(return 0)
				else
					(return 1)
				)
			else
				(return 0)
			)
		)
	)
)

(instance doGuitarJokeScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 2)
			)
			(1
				(messager sayRange: 7 5 7 1 3 self)
			)
			(2
				(sfx number: 522 loop: 1 play:)
				(messager say: 7 5 7 4 self)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance guitarStand of View
	(properties
		noun 7
		sightAngle 40
		x 175
		y 118
		view 518
		cel 1
	)
	
	(method (init)
		(super init: &rest)
		(if (!= global172 1) (= cel 0))
	)
	
	(method (doVerb theVerb)
		(cond 
			((and (== theVerb 5) (!= global172 1)) (curRoom setScript: doGuitarJokeScr))
			(
				(and
					(!= theVerb 6)
					(!= (approachCode doit: theVerb) -32768)
				)
				(messager
					say: noun theVerb (if (!= global172 1) 7 else 0)
				)
			)
			(else (super doVerb: theVerb))
		)
	)
)

(instance stage of Feature
	(properties
		noun 6
		approachX 28
		approachY 60
	)
	
	(method (init)
		(super init: &rest)
		(= sightAngle 26505)
		(self
			setPolygon:
				((Polygon new:)
					type: 3
					init:
						32
						86
						37
						140
						213
						140
						232
						128
						235
						115
						222
						101
						185
						86
						144
						87
						103
						97
						68
						91
						44
						71
						33
						79
					yourself:
				)
		)
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb 4)
				(cond 
					(local0 (messager say: noun theVerb 11))
					((cast contains: burgundy) (messager say: noun theVerb 5))
					(else (curRoom setScript: toStageScr))
				)
			)
			((OneOf theVerb 4 1 2 5 6)
				(messager
					say:
						noun
						theVerb
						(cond 
							(local0 11)
							((cast contains: burgundy) 5)
							(else 6)
						)
				)
			)
			((== theVerb 3) (curRoom setScript: toStageScr))
			(else (super doVerb: theVerb))
		)
	)
	
	(method (onMe theObjOrX)
		(return
			(if (super onMe: theObjOrX)
				(if (walkHandler contains: self)
					(self approachVerbs: 3 4)
				else
					(= _approachVerbs 0)
				)
				1
			else
				0
			)
		)
	)
)

(instance bartender of Actor
	(properties
		noun 9
		sightAngle 40
		x 158
		y 35
		priority 49
		fixPriority 1
		view 513
		signal $4821
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 2)
			(if (and (Btst 45) (not (Btst 214)))
				(messager say: noun theVerb 15)
			else
				(messager
					say:
						noun
						theVerb
						(switch (if (> (++ local7) 2) (= local7 0) else local7)
							(0 8)
							(1 9)
							(2 10)
						)
				)
			)
		else
			(super doVerb: theVerb)
		)
	)
	
	(method (onMe theObjOrX)
		(return
			(if (super onMe: theObjOrX)
				(= approachX x)
				(= approachY (+ y 22))
				(if
					(and
						local0
						(&
							(approachCode doit: (theObjOrX message?))
							_approachVerbs
						)
					)
					(ego setMotion: PolyPath 42 80)
					(= local6 ((ego looper?) oldMover?))
					(CueObj client: self theVerb: (theObjOrX message?))
					(theObjOrX claimed: 1)
					(return 0)
				else
					(return 1)
				)
			else
				(return 0)
			)
		)
	)
	
	(method (cue)
		(self setLoop: 0 setCel: 0)
	)
	
	(method (setHeading h)
		(self
			view: 513
			heading: h
			setLoop: (if (< h 180) 3 else 4) 1
		)
	)
)

(instance palmTrees of PolyFeature
	(properties
		noun 2
		sightAngle 360
	)
	
	(method (init)
		(super init: &rest)
		(self
			addPolygon: palmPoly1 palmPoly2 palmPoly3 palmPoly4 palmPoly5
		)
	)
)

(instance tank of Feature
	(properties
		noun 3
		nsLeft 96
		nsRight 233
		nsBottom 19
		sightAngle 40
		x 160
		y 24
	)
	
	(method (init)
		(super init: &rest)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 128 2 183 1 230 5 230 9 200 24 97 24 96 16 99 6
					yourself:
				)
		)
	)
)

(instance waitress of Actor
	(properties
		x 208
		y 19
		view 512
		signal $4821
		cycleSpeed 15
	)
	
	(method (init)
		(self setPri: 25)
		(super init:)
	)
	
	(method (cue)
		(waitress
			posn: 138 19
			view: 5125
			loop: 0
			cel: 0
			setCycle: Fwd
		)
	)
)

(instance gary of Actor
	(properties
		noun 12
		sightAngle 40
		view 5192
		scaleSignal $0004
	)
	
	(method (init)
		(self
			view: 5192
			loop: 1
			cel: 0
			posn: 328 110
			setSpeed: 6
			setStep: 3 2
			scaleX: 115
			scaleY: 115
			setCycle: Walk
			setPri: -1
			ignoreActors: 1
			setScale:
		)
		(super init: &rest)
	)
	
	(method (cue)
		(if (not (curRoom inset?))
			(self init: setScript: garyScr)
			(garyTimer dispose:)
		else
			(garyTimer setReal: gary 10)
		)
	)
)

(instance matchInset of Inset
	(properties
		picture -2
		view 5141
		x 86
		y 25
		noun 10
	)
	
	(method (init)
		(if (== (theMusic number?) 514) (theMusic pause:))
		(super init: &rest)
		(gGraphMenuBar state: (| (gGraphMenuBar state?) $0004))
		(Bset 86)
		((ScriptID 0 11) init: curRoom)
	)
	
	(method (dispose)
		(Bclr 86)
		((ScriptID 0 11) dispose:)
		(gGraphMenuBar state: (& (gGraphMenuBar state?) $fffb))
		(super dispose:)
		(if (== (theMusic number?) 514) (theMusic pause: 0))
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 5)
			(if (not (ego has: 25))
				(curRoom setScript: getMatchScr)
			else
				(messager say: noun theVerb 21)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance tray of View
	(properties
		x 81
		y 21
		priority 40
		fixPriority 1
		view 510
		loop 3
	)
)

(instance phallicFruit of Feature
	(properties
		noun 18
	)
	
	(method (init)
		(self
			setPolygon: ((Polygon new:)
				init: 156 24 170 24 170 33 156 33
				yourself:
			)
		)
		(super init: &rest)
	)
)

(instance songManager of Script
	(properties)
	
	(method (cue)
		(if (not register)
			(nextSongTimer setReal: self 5)
			(burgundy setCycle: 0)
			(= register 1)
		else
			(theMusic number: 515 loop: -1 play:)
			(burgundy setCycle: Fwd)
		)
	)
)

(instance takeGownScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego get: 17)
				(insetDress dispose:)
				(= cycles 2)
			)
			(1
				(theGame changeScore: 13 216)
				(messager say: 14 5 (if (Btst 66) 12 else 0) 0 self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance getMatchScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= ticks 60)
			)
			(1
				(theGame changeScore: 4 217)
				(ego get: 25)
				(messager say: 10 5 0 0 self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance toStageScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego hide:)
				(= ticks 60)
			)
			(1
				(= local0 1)
				(= gGLarryRoomObstacles (curRoom obstacles?))
				(curRoom obstacles: 0)
				(curRoom
					addObstacle:
						(stage onMeCheck?)
						((Polygon new:)
							type: 2
							init: 160 103 193 103 193 114 160 114
							yourself:
						)
				)
				(if (cast contains: guitarStand)
					(curRoom
						addObstacle:
							((Polygon new:)
								type: 2
								init: 185 115 215 115 215 123 185 123
								yourself:
							)
					)
				)
				(ego
					show:
					setPri: 117
					posn: 39 87
					setMotion: MoveTo 52 104 self
				)
			)
			(2
				(cond 
					(
						(and
							(or (talkers size:) (Print dialog?))
							(Print dialog?)
						)
						(-- state)
						(= ticks 60)
					)
					(local3 (messager say: 6 4 6 0 self) (= local3 0))
					(else (= cycles 2))
				)
			)
			(3
				(walkHandler delete: stage add: curRoom)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance fromStageScr of Script
	(properties)
	
	(method (changeState newState &tmp cueObjClient)
		(switch (= state newState)
			(0
				(= register (if (== local6 (ego mover?)) 1 else 0))
				(theGame handsOff:)
				(ego setMotion: MoveTo 39 87 self)
			)
			(1 (ego hide:) (= ticks 60))
			(2
				(ego show: posn: 16 63 setMotion: MoveTo 45 60 self)
			)
			(3
				(walkHandler delete: curRoom add: stage)
				(= local0 0)
				(ego setPri: -1)
				((curRoom obstacles?) delete: (stage onMeCheck?))
				((curRoom obstacles?) dispose:)
				(curRoom obstacles: gGLarryRoomObstacles)
				(= gGLarryRoomObstacles 0)
				(theGame handsOn:)
				(if register
					(= cueObjClient (CueObj client?))
					(CueObj state: 0 cycles: 0)
					(ego
						setMotion:
							PolyPath
							(cueObjClient approachX?)
							(cueObjClient approachY?)
							CueObj
					)
				)
				(self dispose:)
			)
		)
	)
)

(instance unplugCordScr of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(garyTimer dispose:)
				(ego
					view: 5194
					loop: 0
					cel: 0
					setPri: 109
					setSpeed: 13
					setCycle: End self
				)
			)
			(1
				(sfx number: 520 loop: 1 play:)
				(if (burgundy cycler?) (burgundy setCycle: End))
				(cord cel: 1)
				(= cycles 2)
				(if (cast contains: burgundy) (theMusic stop:))
			)
			(2 (= ticks 60))
			(3
				(if (cast contains: burgundy)
					(theGame changeScore: 10 212)
				)
				(messager say: 11 4 0 0 self)
			)
			(4 (= cycles 2))
			(5
				(ego setLoop: 1 setCel: 0)
				(= ticks 20)
			)
			(6
				(ego normalize: 900 8 1 setCel: 7 setPri: -1)
				(= cycles 2)
			)
			(7
				(if (not (cast contains: burgundy))
					(theGame handsOn:)
					(self dispose:)
				else
					(= ticks 60)
				)
			)
			(8
				(burgundy setCycle: 0)
				(= global172 29)
				(messager
					sayRange:
						5
						0
						(if (not (Bset 51)) (= local9 1) 16 else (Bset 98) 17)
						1
						2
						self
				)
			)
			(9 (= ticks 120))
			(10
				(messager say: 5 0 (if local9 16 else 17) 3 self)
			)
			(11
				(theMusic2 number: 200 loop: -1 play: fade: 80 25 10 0)
				(client setScript: burgundySitScr)
			)
		)
	)
)

(instance burgundySitScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= global172 14)
				(burgundy
					posn: 196 119
					view: 5151
					loop: 0
					cel: 6
					setSpeed: 8
					setCycle: CT 3 -1 self
				)
			)
			(1
				(sfx number: 523 loop: 1 play:)
				(guitarStand cel: 0)
				(burgundy setCycle: Beg self)
			)
			(2
				(burgundy
					posn: 175 117
					view: 517
					setLoop: 3 1
					setSpeed: 6
					setCycle: Walk
					setMotion: MoveTo 160 112 self
				)
			)
			(3
				(burgundy
					setPri: 117
					loop: 1
					setMotion: MoveTo 157 88 self
				)
			)
			(4
				(burgundy
					view: 516
					loop: 0
					cel: 0
					z: 10
					y: (+ (burgundy y?) 10)
					setSpeed: 8
					setCycle: End self
				)
			)
			(5
				(garyTimer setReal: gary 40)
				(burgWhineTimer setReal: burgundy 20)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance giveBeerScr of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(if (Btst 214)
					(theGame handsOff:)
					(theGame changeScore: 8 215)
					(= gLarryRoom curRoom)
					(theIconBar enableIcon: (ScriptID 0 8) show:)
					(= register 1)
					(++ state)
					(= local5 5)
				else
					(theGame changeScore: 6 214)
					(= register 0)
					(= local5 6)
				)
				(ego put: 3 setHeading: 180 self)
			)
			(1
				(theGame handsOn:)
				(= cycles 2)
			)
			(2
				(burgundy view: 5161 loop: 0 cel: 0 setCycle: End self)
			)
			(3
				(burgundy loop: 1 cel: 0 setCycle: Fwd)
				(burgSFX number: 517 play:)
				(= cycles 2)
			)
			(4 (= ticks 160))
			(5
				(burgSFX stop:)
				(burgundy loop: 0 cel: 3 setCycle: Beg self)
			)
			(6
				(= temp0 (- (if register 6 else 7) local5))
				(if (not register)
					(if (== temp0 6)
						(= cycles 2)
					else
						(messager say: 5 0 4 temp0 self)
					)
				else
					(if (== temp0 3) (burgSFX number: 516 loop: 1 play:))
					(if (== temp0 5)
						(messager sayRange: 5 0 31 temp0 9 self)
					else
						(messager say: 5 0 31 temp0 self)
					)
				)
			)
			(7
				(if (-- local5) (= state 1))
				(cond 
					(register (= cycles 1))
					((not local5) (= ticks 60))
					((OneOf global181 2 1) (= ticks 300))
					((cast contains: gary) 0)
					((== local5 3) (gary init: setScript: garyScr))
					(else (= ticks 300))
				)
			)
			(8
				(if register
					(client setScript: secondBeerScr)
				else
					(client setScript: backToGuitarScr)
				)
			)
		)
	)
)

(instance backToGuitarScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(= global172 3)
				(if (not (Btst 215))
					(messager say: 5 0 4 6 self)
				else
					(= cycles 2)
				)
			)
			(2
				(burgundy
					view: 516
					setLoop: 0
					setCel: 8
					z: 0
					y: (- (burgundy y?) 10)
					setCycle: Beg self
				)
			)
			(3
				(burgundy
					setSpeed: 6
					view: 517
					setLoop: 2 1
					setCel: 0
					setCycle: Walk
					setMotion: MoveTo 160 112 self
				)
			)
			(4
				(burgundy setPri: 130 setMotion: MoveTo 175 117 self)
			)
			(5
				(burgundy
					posn: 196 119
					setSpeed: 8
					view: 5151
					setLoop: 0
					setCel: 0
					setCycle: CT 3 1 self
				)
			)
			(6
				(burgundy setCycle: End self)
				(guitarStand cel: 1)
			)
			(7
				(= global172 1)
				(if (!= (theMusic2 prevSignal?) -1) (theMusic2 fade:))
				(theMusic number: 515 loop: -1 play:)
				(burgundy
					posn: 175 117
					view: 515
					setLoop: 0
					setSpeed: 10
					setCycle: Fwd
				)
				(Bclr 37)
				(self dispose:)
			)
		)
	)
)

(instance secondBeerScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(burgundy
					view: 516
					setLoop: 0
					setCel: 8
					y: (- (burgundy y?) 10)
					z: 0
					setCycle: Beg self
				)
			)
			(1
				(burgundy
					setStep: 3 3
					view: 517
					setLoop: 2 1
					setCycle: Walk
					setSpeed: 10
					setMotion: MoveTo 98 199 self
				)
				(= cycles 2)
			)
			(2 (messager say: 5 0 31 10))
			(3
				(messager say: 5 0 31 11 self)
			)
			(4
				(walkHandler add: stage)
				(if global205 (proc79_7))
				(burgundy dispose:)
				((inventory at: 17) owner: 510)
				(= gLarryRoom 0)
				(theIconBar disableIcon: (ScriptID 0 8) show:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance from3DoorHallScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego posn: 325 133 setMotion: MoveTo 311 133 self)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance waitressScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks 300))
			(1
				(waitress show: setCycle: End waitress)
				(= cycles 2)
			)
			(2
				(if
					(or
						(bartender mover?)
						((waitress cycler?) isKindOf: Fwd)
					)
					(-- state)
				else
					(bartender setScript: 0)
				)
				(= ticks 20)
			)
			(3
				(bartender setLoop: 4)
				(= ticks 15)
			)
			(4
				(bartender setLoop: 0 setCel: 2)
				(= ticks 60)
			)
			(5 (= ticks 180))
			(6
				(waitress
					posn: 138 19
					view: 5121
					loop: 0
					cel: 0
					setCycle: End self
				)
			)
			(7
				(waitress setCycle: Beg self)
			)
			(8
				(waitress cue:)
				(bartender setLoop: 1 setCycle: End self)
			)
			(9
				(bartender setLoop: 0 setCel: 2)
				(waitress view: 5121 setLoop: 1 setCycle: End self)
			)
			(10
				(waitress setCycle: End self)
			)
			(11
				(waitress cue:)
				(= ticks 30)
			)
			(12
				(waitress view: 5122 setLoop: 0 setCycle: End self)
			)
			(13
				(waitress setCycle: End self)
			)
			(14
				(waitress cue:)
				(= ticks 30)
			)
			(15
				(waitress
					posn: 125 19
					view: 5123
					setLoop: 1
					setCel: 0
					setCycle: End self
				)
			)
			(16
				(waitress hide:)
				(= ticks 120)
			)
			(17
				(= register (waitress y?))
				(waitress
					show:
					view: 5123
					setLoop: 0
					y: 44
					setCycle: Fwd
					ignoreActors: 1
					setMotion: MoveTo (waitress x?) register self
				)
			)
			(18 (= ticks 30))
			(19
				(waitress
					view: 5122
					setLoop: 1
					setCel: 0
					setCycle: End self
				)
			)
			(20
				(waitress setCycle: Beg self)
			)
			(21
				(waitress view: 5123 loop: 0 setCycle: Fwd)
				(= ticks 30)
			)
			(22
				(bartender
					view: 513
					loop: 4
					setCycle: Walk
					setMotion: MoveTo 101 35 self
				)
			)
			(23
				(bartender
					posn: 94 35
					view: 5131
					setCel: 0
					setLoop: 1
					setCycle: CT 11 1 self
				)
			)
			(24
				(bartender setCycle: End self)
			)
			(25 (= ticks 10))
			(26
				(bartender
					posn: 101 35
					view: 513
					setMotion: MoveTo 120 36 bartender
				)
				(= ticks 20)
			)
			(27
				(waitress
					view: 5123
					setLoop: 1
					setCel: 1
					setCycle: End self
				)
			)
			(28
				(waitress hide:)
				(= ticks 60)
			)
			(29
				(waitress
					show:
					posn: 150 19
					view: 5124
					loop: 0
					cel: 0
					setCycle: End self
				)
			)
			(30
				(bartender setScript: bartenderScr)
				(client dispose:)
			)
		)
	)
)

(instance bartenderScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== register -16)
					(self dispose:)
					(curRoom setScript: waitressScr)
					(return)
				)
				(= ticks (Random 900 1200))
				(repeat
					(= state (Random 1 3))
					(breakif (!= register state))
				)
				(-- state)
			)
			(1
				(= register 1)
				(client setMotion: MoveTo 181 34 self)
				(= state 3)
			)
			(2
				(= register 2)
				(client setMotion: MoveTo 158 35 self)
				(= state 3)
			)
			(3
				(= register 3)
				(client setMotion: MoveTo 120 36 self)
				(= state 3)
			)
			(4
				(client setLoop: 2 1 cel: 0 setCycle: Fwd)
				(= ticks (Random 120 300))
			)
			(5
				(client view: 510 setCycle: 0 setLoop: 1 1 cel: 0)
				(= cycles 2)
				(= state -1)
			)
		)
	)
)

(instance walkInScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks 30))
			(1
				(barDoor setCycle: End)
				(= ticks 30)
			)
			(2
				(ego setMotion: MoveTo 56 53 self)
			)
			(3
				(ego setPri: -1)
				(barDoor setCycle: Beg self)
			)
			(4
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance enterPoolScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (theMusic handle?) (theMusic pause:))
				(LoadMany 128 5101 901)
				(ego
					setSpeed: 8
					view: 901
					setLoop: 6
					setCel: 0
					setCycle: End self
				)
			)
			(1
				(sfx number: 518 loop: 1 play:)
				(barDoor view: 5101 setCycle: End self)
				(= ticks 10)
			)
			(2
				(ego view: 5101 setLoop: 2 setCel: 0)
			)
			(3 (= ticks 180))
			(4 (EgoDead 13 self))
			(5
				(if (theMusic handle?) (theMusic pause: 0))
				(ego normalize: 900 8 1 setCel: 2)
				(barDoor view: 510 setLoop: 0 setCel: 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance goToInsetScr of Script
	(properties)
	
	(method (changeState newState)
		(asm
			lap      newState
			aTop     state
			push    
			dup     
			ldi      0
			eq?     
			bnt      code_2b8b
			pushi    #handsOff
			pushi    0
			lag      theGame
			send     4
			pushi    #contains
			pushi    1
			lofsa    gary
			push    
			lag      cast
			send     6
			bnt      code_2b7d
			pushi    #state
			pushi    0
			lofsa    garyScr
			send     4
			push    
			ldi      5
			lt?     
			bnt      code_2b78
			ldi      1
			jmp      code_2b7f
code_2b78:
			ldi      2
			jmp      code_2b7f
code_2b7d:
			ldi      3
code_2b7f:
			sag      global181
			pushi    #cue
			pushi    0
			self     4
			jmp      code_2c29
code_2b8b:
			dup     
			ldi      1
			eq?     
			bnt      code_2be5
			pushi    #size
			pushi    0
			lag      talkers
			send     4
			bt       code_2ba9
			pushi    #dialog
			pushi    0
			class    Print
			send     4
			bnt      code_2bb5
code_2ba9:
			bnt      code_2bb5
			dpToa    state
			ldi      30
			aTop     ticks
			jmp      code_2c29
code_2bb5:
			pushi    1
			pushi    46
			calle    Bset,  2
			not     
			bnt      code_2bdb
			pToa     register
			not     
			bnt      code_2bdb
			pushi    #say
			pushi    5
			dup     
			pushi    1
			lsg      global172
			pushi    0
			pushSelf
			lag      messager
			send     14
			jmp      code_2c29
code_2bdb:
			pushi    #cue
			pushi    0
			self     4
			jmp      code_2c29
code_2be5:
			dup     
			ldi      2
			eq?     
			bnt      code_2bf3
			ldi      30
			aTop     ticks
			jmp      code_2c29
code_2bf3:
			dup     
			ldi      3
			eq?     
			bnt      code_2c29
			pushi    #size
			pushi    0
			lag      talkers
			send     4
			bt       code_2c10
			pushi    #dialog
			pushi    0
			class    Print
			send     4
			bnt      code_2c10
code_2c10:
			not     
			bnt      code_2c23
			pushi    #newRoom
			pushi    1
			pushi    520
			lag      curRoom
			send     6
			jmp      code_2c29
code_2c23:
			dpToa    state
			ldi      30
			aTop     ticks
code_2c29:
			toss    
			ret     
		)
	)
)

(instance syncSwimmerScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(syncSwimmer1 hide:)
				(syncSwimmer2 hide:)
				(= state
					(-
						(switch (Random 1 5)
							(1 1)
							(2 2)
							(3 4)
							(4 6)
							(5 8)
						)
						1
					)
				)
				(= ticks (Random 120 900))
			)
			(1
				(syncSwimmer1
					view: 511
					loop: 0
					cel: 0
					posn: 163 14
					show:
					setPri: 24
					setCycle: End self
				)
				(= state -1)
			)
			(2
				(syncSwimmer1
					view: 5111
					loop: 0
					cel: 0
					posn: 140 3
					show:
					setPri: 24
					setCycle: End self
				)
				(syncSwimmer2
					view: 5111
					loop: 0
					cel: 0
					posn: 185 3
					show:
					setPri: 24
					setCycle: End self
				)
			)
			(3 (= state -1))
			(4
				(syncSwimmer1
					view: 5112
					loop: 0
					cel: 0
					posn: 141 -5
					show:
					setPri: 24
					setCycle: End self
				)
				(syncSwimmer2
					view: 5112
					loop: 0
					cel: 0
					posn: 184 -5
					show:
					setPri: 24
					setCycle: End self
				)
			)
			(5 (= state -1))
			(6
				(syncSwimmer1
					view: 5113
					loop: 0
					cel: 0
					posn: 141 -5
					show:
					setPri: 24
					setCycle: End self
				)
				(syncSwimmer2
					view: 5113
					loop: 1
					cel: 0
					posn: 184 -5
					show:
					setPri: 24
					setCycle: End self
				)
			)
			(7 (= state -1))
			(8
				(syncSwimmer1
					view: 5114
					loop: 0
					cel: 0
					posn: 165 8
					show:
					setPri: 24
					setCycle: End self
				)
				(syncSwimmer2
					view: 5114
					loop: 1
					cel: 0
					posn: 147 20
					show:
					setPri: 24
					setCycle: End self
				)
			)
			(9 (= state -1))
		)
	)
)

(instance fish1 of Prop
	(properties
		x 276
		y 57
		view 5104
		loop 2
		signal $4021
	)
	
	(method (cue)
		(self hide:)
	)
)

(instance fish2 of Prop
	(properties
		x 276
		y 50
		view 5104
		loop 1
		signal $4021
	)
	
	(method (cue)
		(self hide:)
	)
)

(instance fish3 of Prop
	(properties
		x 279
		y 64
		view 5104
		signal $4021
	)
	
	(method (cue)
		(self hide:)
	)
)

(instance fishScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks (Random 60 200)))
			(1
				(switch (Random 0 2)
					(0
						0
						(if (not (fish1 cycler?))
							(fish1
								setCel: 0
								show:
								setPri: 95
								cycleSpeed: (Random 6 15)
								setCycle: End fish1
							)
						)
					)
					(1
						1
						(if (not (fish2 cycler?))
							(fish2
								setCel: 0
								show:
								setPri: 95
								cycleSpeed: (Random 6 15)
								setCycle: End fish2
							)
						)
					)
					(2
						2
						(if (not (fish3 cycler?))
							(fish3
								setCel: 0
								show:
								setPri: 95
								cycleSpeed: (Random 6 15)
								setCycle: End fish3
							)
						)
					)
				)
				(= state -1)
				(self cue:)
			)
		)
	)
)

(instance garyTimer of Timer
	(properties)
)

(instance burgWhineTimer of Timer
	(properties)
)

(instance palmPoly1 of Polygon
	(properties)
	
	(method (init)
		(super
			init: 0 0 56 0 77 5 48 18 53 29 54 48 42 55 30 64 20 65 5 64 0 62
		)
	)
)

(instance palmPoly2 of Polygon
	(properties)
	
	(method (init)
		(super
			init: 319 0 319 21 305 30 294 44 284 41 271 40 250 41 236 22 224 11 241 0
		)
	)
)

(instance palmPoly3 of Polygon
	(properties)
	
	(method (init)
		(super
			init: 319 90 307 101 294 90 293 77 299 65 314 60 308 55 319 57
		)
	)
)

(instance palmPoly4 of Polygon
	(properties)
	
	(method (init)
		(super init: 308 139 289 125 303 105 319 110 319 132)
	)
)

(instance palmPoly5 of Polygon
	(properties)
	
	(method (init)
		(super
			init:
				2
				124
				11
				114
				23
				115
				29
				113
				27
				103
				10
				86
				17
				75
				25
				74
				43
				82
				40
				89
				49
				97
				56
				100
				58
				118
				48
				124
				40
				122
				31
				127
				23
				139
				12
				140
		)
	)
)

(instance nextSongTimer of Timer
	(properties)
)

(instance sfx of Sound
	(properties
		flags $0001
	)
)

(instance burgSFX of Sound
	(properties
		flags $0001
	)
)

(instance toHallScr of Script
	(properties)
	
	(method (changeState newState)
		(asm
			lap      newState
			aTop     state
			push    
			dup     
			ldi      0
			eq?     
			bnt      code_314b
			pushi    #handsOff
			pushi    0
			lag      theGame
			send     4
			pTos     register
			ldi      3
			eq?     
			bnt      code_3121
			pushi    316
			pushi    4
			class    MoveTo
			push    
			pushi    #x
			pushi    0
			lag      ego
			send     4
			push    
			ldi      20
			add     
			push    
			pushi    #y
			pushi    0
			lag      ego
			send     4
			push    
			ldi      60
			add     
			push    
			pushSelf
			lag      ego
			send     12
			jmp      code_31a6
code_3121:
			pushi    316
			pushi    4
			class    MoveTo
			push    
			pushi    #x
			pushi    0
			lag      ego
			send     4
			push    
			ldi      15
			add     
			push    
			pushi    #y
			pushi    0
			lag      ego
			send     4
			push    
			ldi      15
			add     
			push    
			pushSelf
			lag      ego
			send     12
			jmp      code_31a6
code_314b:
			dup     
			ldi      1
			eq?     
			bnt      code_31a6
			pushi    #client
			pushi    0
			lag      theMusic
			send     4
			bnt      code_316e
			pushi    #client
			pushi    1
			pushi    0
			pushi    52
			pushi    1
			pushi    0
			pushi    248
			pushi    0
			lag      theMusic
			send     16
code_316e:
			pushi    #size
			pushi    0
			lag      talkers
			send     4
			bt       code_3184
			pushi    #dialog
			pushi    0
			class    Print
			send     4
			bnt      code_3184
code_3184:
			not     
			bnt      code_3197
			pushi    #newRoom
			pushi    1
			pushi    505
			lag      curRoom
			send     6
			jmp      code_31a6
code_3197:
			pushi    #handsOff
			pushi    0
			lag      theGame
			send     4
			dpToa    state
			ldi      30
			aTop     ticks
code_31a6:
			toss    
			ret     
		)
	)
)

(instance garyScr of Script
	(properties)
	
	(method (changeState newState)
		(asm
			lap      newState
			aTop     state
			push    
			dup     
			ldi      0
			eq?     
			bnt      code_31e8
			pushi    #setMotion
			pushi    8
			class    DPath
			push    
			pushi    319
			pushi    110
			pushi    250
			pushi    104
			pushi    209
			pushi    82
			pushSelf
			lofsa    gary
			send     20
			lal      local9
			bnt      code_31dd
			ldi      16
			jmp      code_31df
code_31dd:
			ldi      17
code_31df:
			aTop     register
			ldi      100
			aTop     ticks
			jmp      code_36af
code_31e8:
			dup     
			ldi      1
			eq?     
			bnt      code_323a
			pushi    #size
			pushi    0
			lag      talkers
			send     4
			bt       code_3206
			pushi    #dialog
			pushi    0
			class    Print
			send     4
			bnt      code_3206
code_3206:
			not     
			bnt      code_3231
			pushi    #script
			pushi    0
			lofsa    burgundy
			send     4
			push    
			lofsa    goToInsetScr
			ne?     
			bnt      code_3231
			pushi    #say
			pushi    5
			dup     
			pushi    0
			pTos     register
			pushi    5
			pushSelf
			lag      messager
			send     14
			jmp      code_36af
code_3231:
			dpToa    state
			ldi      30
			aTop     ticks
			jmp      code_36af
code_323a:
			dup     
			ldi      2
			eq?     
			bnt      code_3248
			ldi      30
			aTop     ticks
			jmp      code_36af
code_3248:
			dup     
			ldi      3
			eq?     
			bnt      code_329a
			pushi    #size
			pushi    0
			lag      talkers
			send     4
			bt       code_3266
			pushi    #dialog
			pushi    0
			class    Print
			send     4
			bnt      code_3266
code_3266:
			not     
			bnt      code_3291
			pushi    #script
			pushi    0
			lofsa    burgundy
			send     4
			push    
			lofsa    goToInsetScr
			ne?     
			bnt      code_3291
			pushi    #say
			pushi    4
			pushi    5
			pushi    0
			pTos     register
			pushi    6
			lag      messager
			send     12
			jmp      code_36af
code_3291:
			dpToa    state
			ldi      30
			aTop     ticks
			jmp      code_36af
code_329a:
			dup     
			ldi      4
			eq?     
			bnt      code_32bd
			pushi    #setMotion
			pushi    6
			class    DPath
			push    
			pushi    157
			pushi    78
			pushi    112
			pushi    84
			pushSelf
			lofsa    gary
			send     16
			jmp      code_36af
code_32bd:
			dup     
			ldi      5
			eq?     
			bnt      code_32e5
			pushi    #view
			pushi    1
			pushi    519
			pushi    254
			pushi    1
			pushi    0
			pushi    326
			pushi    1
			pushi    0
			pushi    236
			pushi    2
			class    End
			push    
			pushSelf
			lofsa    gary
			send     26
			jmp      code_36af
code_32e5:
			dup     
			ldi      6
			eq?     
			bnt      code_3307
			pushi    #view
			pushi    1
			pushi    5192
			pushi    15
			pushi    1
			pushi    2
			pushi    16
			pushi    1
			pushi    0
			lofsa    gary
			send     18
			ldi      4
			aTop     cycles
			jmp      code_36af
code_3307:
			dup     
			ldi      7
			eq?     
			bnt      code_3333
			pushi    #loop
			pushi    1
			pushi    1
			pushi    16
			pushi    1
			pushi    0
			pushi    236
			pushi    1
			class    Walk
			push    
			pushi    316
			pushi    4
			class    MoveTo
			push    
			pushi    87
			dup     
			pushSelf
			lofsa    gary
			send     30
			jmp      code_36af
code_3333:
			dup     
			ldi      8
			eq?     
			bnt      code_3379
			pushi    #script
			pushi    0
			lofsa    burgundy
			send     4
			push    
			lofsa    giveBeerScr
			eq?     
			bnt      code_335e
			lsg      global181
			ldi      3
			eq?     
			bnt      code_335e
			pushi    #cue
			pushi    0
			lofsa    giveBeerScr
			send     4
code_335e:
			pushi    #setMotion
			pushi    6
			class    DPath
			push    
			pushi    58
			pushi    76
			pushi    28
			pushi    68
			pushSelf
			lofsa    gary
			send     16
			jmp      code_36af
code_3379:
			dup     
			ldi      9
			eq?     
			bnt      code_3390
			pushi    #hide
			pushi    0
			lofsa    gary
			send     4
			ldi      120
			aTop     ticks
			jmp      code_36af
code_3390:
			dup     
			ldi      10
			eq?     
			bnt      code_33e2
			pushi    #setPri
			pushi    1
			pushi    200
			lofsa    guitarStand
			send     6
			pushi    #setSpeed
			pushi    1
			pushi    6
			pushi    349
			pushi    2
			pushi    4
			pushi    3
			pushi    331
			pushi    1
			pushi    0
			pushi    127
			pushi    0
			pushi    74
			pushi    1
			pushi    130
			pushi    325
			pushi    2
			pushi    28
			pushi    111
			pushi    15
			pushi    1
			pushi    0
			pushi    316
			pushi    4
			class    MoveTo
			push    
			pushi    101
			pushi    113
			pushSelf
			lofsa    gary
			send     56
			jmp      code_36af
code_33e2:
			dup     
			ldi      11
			eq?     
			bnt      code_3408
			pushi    #view
			pushi    1
			pushi    519
			pushi    15
			pushi    1
			pushi    0
			pushi    16
			pushi    1
			pushi    0
			pushi    236
			pushi    2
			class    End
			push    
			pushSelf
			lofsa    gary
			send     26
			jmp      code_36af
code_3408:
			dup     
			ldi      12
			eq?     
			bnt      code_3444
			pushi    #view
			pushi    1
			pushi    5192
			pushi    254
			pushi    1
			pushi    0
			pushi    326
			pushi    1
			pushi    0
			pushi    236
			pushi    1
			class    Walk
			push    
			pushi    351
			pushi    1
			pushi    6
			pushi    316
			pushi    4
			class    MoveTo
			push    
			pushi    175
			pushi    115
			pushSelf
			lofsa    gary
			send     42
			jmp      code_36af
code_3444:
			dup     
			ldi      13
			eq?     
			bnt      code_3471
			pushi    #setSpeed
			pushi    1
			pushi    8
			pushi    254
			pushi    1
			pushi    2
			pushi    326
			pushi    1
			pushi    2
			pushi    236
			pushi    4
			class    CT
			push    
			pushi    4
			pushi    1
			pushSelf
			lofsa    gary
			send     30
			jmp      code_36af
code_3471:
			dup     
			ldi      14
			eq?     
			bnt      code_3483
			ldi      4
			sal      local11
			ldi      20
			aTop     ticks
			jmp      code_36af
code_3483:
			dup     
			ldi      15
			eq?     
			bnt      code_34ab
			pushi    #number
			pushi    1
			pushi    524
			pushi    15
			pushi    1
			pushi    1
			pushi    51
			pushi    0
			lofsa    burgSFX
			send     16
			-al      local11
			bnt      code_34a4
			dpToa    state
code_34a4:
			ldi      25
			aTop     ticks
			jmp      code_36af
code_34ab:
			dup     
			ldi      16
			eq?     
			bnt      code_3500
			pushi    #size
			pushi    0
			lag      talkers
			send     4
			bt       code_34c9
			pushi    #dialog
			pushi    0
			class    Print
			send     4
			bnt      code_34c9
code_34c9:
			not     
			bnt      code_34f7
			pushi    #script
			pushi    0
			lofsa    burgundy
			send     4
			push    
			lofsa    goToInsetScr
			ne?     
			bnt      code_34f7
			pushi    #sayRange
			pushi    6
			pushi    5
			pushi    0
			pTos     register
			pushi    7
			pushi    8
			pushSelf
			lag      messager
			send     16
			jmp      code_36af
code_34f7:
			dpToa    state
			ldi      30
			aTop     ticks
			jmp      code_36af
code_3500:
			dup     
			ldi      17
			eq?     
			bnt      code_351d
			pushi    #setCycle
			pushi    4
			class    CT
			push    
			pushi    2
			pushi    65535
			pushSelf
			lofsa    gary
			send     12
			jmp      code_36af
code_351d:
			dup     
			ldi      18
			eq?     
			bnt      code_354e
			pushi    #setLoop
			pushi    1
			pushi    0
			pushi    236
			pushi    1
			class    Walk
			push    
			pushi    351
			pushi    1
			pushi    6
			pushi    316
			pushi    4
			class    MoveTo
			push    
			pushi    226
			pushi    118
			pushSelf
			lofsa    gary
			send     30
			jmp      code_36af
code_354e:
			dup     
			ldi      19
			eq?     
			bnt      code_35a5
			pushi    #script
			pushi    0
			lofsa    burgundy
			send     4
			push    
			lofsa    giveBeerScr
			eq?     
			bnt      code_3579
			lsg      global181
			ldi      3
			eq?     
			bnt      code_3579
			pushi    #cue
			pushi    0
			lofsa    giveBeerScr
			send     4
code_3579:
			pushi    #view
			pushi    1
			pushi    5193
			pushi    254
			pushi    1
			pushi    0
			pushi    326
			pushi    1
			pushi    0
			pushi    351
			pushi    1
			pushi    8
			pushi    236
			pushi    4
			class    CT
			push    
			pushi    3
			pushi    1
			pushSelf
			lofsa    gary
			send     36
			jmp      code_36af
code_35a5:
			dup     
			ldi      20
			eq?     
			bnt      code_35e1
			pushi    #number
			pushi    1
			pushi    3
			pushi    15
			pushi    1
			pushi    1
			pushi    51
			pushi    0
			lofsa    burgSFX
			send     16
			pushi    1
			pushi    37
			calle    Bclr,  2
			pushi    #cel
			pushi    1
			pushi    0
			lofsa    cord
			send     6
			pushi    #setCycle
			pushi    2
			class    End
			push    
			pushSelf
			lofsa    gary
			send     8
			jmp      code_36af
code_35e1:
			dup     
			ldi      21
			eq?     
			bnt      code_3602
			pushi    #handsOff
			pushi    0
			lag      theGame
			send     4
			pushi    #setMotion
			pushi    1
			pushi    0
			lag      ego
			send     6
			ldi      30
			aTop     ticks
			jmp      code_36af
code_3602:
			dup     
			ldi      22
			eq?     
			bnt      code_3654
			pushi    #size
			pushi    0
			lag      talkers
			send     4
			bt       code_3620
			pushi    #dialog
			pushi    0
			class    Print
			send     4
			bnt      code_3620
code_3620:
			not     
			bnt      code_364b
			pushi    #script
			pushi    0
			lofsa    burgundy
			send     4
			push    
			lofsa    goToInsetScr
			ne?     
			bnt      code_364b
			pushi    #say
			pushi    5
			dup     
			pushi    0
			pTos     register
			pushi    9
			pushSelf
			lag      messager
			send     14
			jmp      code_36af
code_364b:
			dpToa    state
			ldi      30
			aTop     ticks
			jmp      code_36af
code_3654:
			dup     
			ldi      23
			eq?     
			bnt      code_368b
			pushi    #view
			pushi    1
			pushi    5192
			pushi    254
			pushi    1
			pushi    1
			pushi    351
			pushi    1
			pushi    6
			pushi    236
			pushi    1
			class    Walk
			push    
			pushi    316
			pushi    4
			class    MoveTo
			push    
			pushi    175
			pushi    115
			pushSelf
			lofsa    gary
			send     36
			jmp      code_36af
code_368b:
			dup     
			ldi      24
			eq?     
			bnt      code_36af
			pushi    #setPri
			pushi    1
			pushi    118
			lofsa    guitarStand
			send     6
			pushi    #setScript
			pushi    3
			lofsa    gary2Scr
			push    
			pushi    0
			pTos     register
			pToa     client
			send     10
code_36af:
			toss    
			ret     
		)
	)
)

(instance gary2Scr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gary
					setSpeed: 8
					setLoop: 2
					setCel: 5
					setCycle: CT 4 -1 self
				)
				(= local11 4)
			)
			(1
				(burgSFX number: 524 loop: 1 play:)
				(if (-- local11) (-- state))
				(= ticks 25)
			)
			(2
				(messager say: 5 0 register 10 self)
			)
			(3
				(messager say: 5 0 register 11 self oneOnly: 0)
			)
			(4
				(theGame handsOn:)
				(= seconds 6)
			)
			(5 (gary setCycle: CT 5 1 self))
			(6
				(if
					(and
						(== (burgundy script?) giveBeerScr)
						(== global181 3)
					)
					(giveBeerScr cue:)
				)
				(gary
					setSpeed: 6
					loop: 1
					setCycle: Walk
					setMotion: MoveTo 28 111 self
				)
			)
			(7
				(if
					(not
						(OneOf (burgundy script?) goToInsetScr giveBeerScr)
					)
					(burgundy setScript: backToGuitarScr)
				)
				(= local9 0)
				(gary dispose:)
			)
		)
	)
)
