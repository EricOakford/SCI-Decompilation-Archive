;;; Sierra Script 1.0 - (do not remove this comment)
(script# 130)
(include sci.sh)
(use Main)
(use Kq6Procs)
(use PolyPath)
(use Feature)
(use Sound)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	pullOutMapScr 0
	MapScr 1
)

(local
	theCast
	theFeatures
	theAddToPics
	theMouseDownHandler
	theKeyDownHandler
	local5
	theWalkHandler
	curRoomObstacles
	theMists
	local9
	[local10 7]
)
(instance pullOutMapScr of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(= temp0 0)
				(while (< temp0 6)
					(= [local10 temp0]
						(& ((theIconBar at: temp0) signal?) $0004)
					)
					(++ temp0)
				)
				(theGame handsOff:)
				(theIconBar disable:)
				(messager say: 1 12 5 0 self 0)
			)
			(1
				(switch curRoomNum
					(200
						(if (not (ego inRect: 93 98 284 183))
							(ego setMotion: PolyPath 182 126 self)
						else
							(= cycles 2)
						)
					)
					(450
						(if (not (ego inRect: 24 103 252 155))
							(ego setMotion: PolyPath 176 131 self)
						else
							(= cycles 2)
						)
					)
					(550
						(if (not (ego inRect: 0 79 274 133))
							(ego setMotion: PolyPath 167 101 self)
						else
							(= cycles 2)
						)
					)
					(500
						(if (not (ego inRect: 86 89 318 159))
							(ego setMotion: PolyPath 203 129 self)
						else
							(= cycles 2)
						)
					)
					(else  (= cycles 2))
				)
			)
			(2
				(if (!= (ego cel?) 2)
					(ego setHeading: 180 self)
				else
					(= cycles 2)
				)
			)
			(3
				(ego
					normal: 0
					cycleSpeed: 10
					view: 207
					setLoop: 2
					cel: 0
					setCycle: End self
				)
			)
			(4 (= seconds 2))
			(5
				(if next ((ScriptID 130 1) next: next) (= next 0))
				(curRoom setScript: (ScriptID 130 1))
			)
		)
	)
)

(class MapScr of Script
	(properties
		client 0
		state $ffff
		start 0
		timer 0
		cycles 0
		seconds 0
		lastSeconds 0
		ticks 0
		lastTicks 0
		register 0
		script 0
		caller 0
		next 0
	)
	
	(method (init)
		(= theCast cast)
		(= theFeatures features)
		(= theAddToPics addToPics)
		(= theMouseDownHandler mouseDownHandler)
		(= theKeyDownHandler keyDownHandler)
		(= theWalkHandler walkHandler)
		(= curRoomObstacles (curRoom obstacles?))
		(curRoom obstacles: ((List new:) add: yourself:))
		((= cast (EventHandler new:)) name: {newCast} add:)
		((= features (EventHandler new:)) name: {newFeatures})
		((= addToPics (EventHandler new:)) name: {newATPs} add:)
		((= mouseDownHandler (EventHandler new:))
			name: {newMH}
			add: self
		)
		((= keyDownHandler (EventHandler new:))
			name: {newKH}
			add: self
		)
		((= walkHandler (EventHandler new:)) name: {newWH} add:)
		(if register (inventory hide:) (= register 0))
		(super init: &rest)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(DrawPic 130 dpOPEN_FADEPALETTE)
				(sacredMountain init:)
				(wonder init:)
				(crown init:)
				(beast init:)
				(northMarker init:)
				(mists init:)
				(mapTitle init:)
				(exitFeature init:)
				(theMap init:)
				(mapSound number: 130 loop: 1 play:)
				(= cycles 10)
			)
			(1
				(theGame handsOn:)
				(theIconBar enable: disable: 0 3 4 5 6)
			)
			(2
				(if (cast contains: mistsProp) (mistsProp dispose:))
				(if modelessDialog (modelessDialog dispose:))
				(theGame setCursor: waitCursor)
				(cast
					eachElementDo: #dispose
					eachElementDo: #delete
					release:
					dispose:
				)
				(addToPics dispose:)
				(features delete: self dispose:)
				((curRoom obstacles?) dispose:)
				(curRoom obstacles: curRoomObstacles)
				(= cast theCast)
				(= features theFeatures)
				(= addToPics theAddToPics)
				(UnLoad 128 131)
				(DrawPic (curRoom picture?) dpOPEN_NO_TRANSITION)
				(if addToPics (addToPics doit:))
				(if register
					(theIconBar enable: disable: 2 1)
					(= cycles 15)
				else
					(= next 0)
					(= seconds 2)
				)
			)
			(3
				(if register
					(ego
						normal: 0
						cycleSpeed: 10
						view: 207
						setLoop: 2
						lastCel:
						setCycle: Beg self
					)
				else
					(= seconds 2)
				)
			)
			(4
				(if register
					(ego reset: 2)
					(theGame handsOn:)
					(theIconBar enable: 6)
					(= temp0 0)
					(while (< temp0 6)
						(if (!= [local10 temp0] 0)
							(theIconBar disable: (theIconBar at: temp0))
						)
						(++ temp0)
					)
					(self dispose:)
				else
					(mapSound loop: 1 number: 947 play:)
					(ego cel: 0 setLoop: 0 setCycle: End self)
				)
			)
			(5 (= seconds 1))
			(6
				(theIconBar enable: enable: 6)
				(Bset 103)
				(switch (theMists tpRoom?)
					(200 (ego posn: 182 126))
					(300 (ego posn: 151 134))
					(450 (ego posn: 176 131))
					(550 (ego posn: 167 101))
					(500 (ego posn: 203 129))
				)
				(curRoom newRoom: (theMists tpRoom?))
			)
		)
	)
	
	(method (handleEvent event)
		(return
			(if
				(and
					(not (event modifiers?))
					(& (event type?) evMOUSEKEYBOARD)
					(not (event claimed?))
				)
				(cond 
					(
						(and
							(User canControl:)
							(= theMists (cast firstTrue: #onMe event))
						)
						(if (== theMists mistsProp) (= theMists mists))
						(event claimed: 1)
						(theMists doVerb: ((theIconBar curIcon?) message?))
					)
					((User canControl:)
						(features delete: MapScr)
						(= theMists (features firstTrue: #onMe event))
						(event claimed: 1)
						(theMists doVerb: ((theIconBar curIcon?) message?))
					)
				)
				(return 1)
			else
				0
			)
		)
	)
	
	(method (resetHandlers)
		(mouseDownHandler delete: self dispose:)
		(keyDownHandler delete: self dispose:)
		(walkHandler delete: self dispose:)
		(= mouseDownHandler theMouseDownHandler)
		(= keyDownHandler theKeyDownHandler)
		(= walkHandler theWalkHandler)
	)
)

(instance theMap of Feature
	(properties
		noun 6
		modNum 130
		nsBottom 200
		nsRight 320
	)
)

(class HighliteMap of Feature
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 0
		modNum 130
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		sightAngle 26505
		actions 0
		onMeCheck $6789
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		view 0
		loop 0
		tpRoom 0
		theMapObj 0
	)
	
	(method (doVerb theVerb)
		(= theMists self)
		(if (== theVerb 5) (MapScr resetHandlers:))
		(cond 
			((and (== theVerb 5) (== curRoomNum tpRoom)) (theIconBar enable:) (MapScr register: 1 cue:))
			((== theVerb 5)
				(if (not (Btst 128))
					(Bset 128)
					(theGame givePoints: 1)
				)
				(messager say: noun theVerb 0 0 self 130)
				(theIconBar enable:)
				(theGame handsOff:)
			)
			(else (super doVerb: theVerb &rest))
		)
	)
	
	(method (cue)
		((= theMapObj (Actor new:))
			view: view
			setPri: 15
			loop: loop
			illegalBits: 0
			ignoreActors: 1
			ignoreHorizon: 1
			posn: x y
			init:
			setCycle: End MapScr
		)
		(mapSound number: 131 loop: 1 play:)
	)
)

(instance mistsProp of Prop
	(properties
		x 256
		y 142
		onMeCheck $0080
		view 132
		loop 2
	)
	
	(method (doVerb)
		(mists doVerb: &rest)
	)
)

(instance sacredMountain of HighliteMap
	(properties
		x 156
		y 49
		noun 9
		onMeCheck $0002
		view 131
		tpRoom 300
	)
)

(instance crown of HighliteMap
	(properties
		x 124
		y 129
		noun 1
		onMeCheck $0008
		view 131
		loop 1
		tpRoom 200
	)
)

(instance wonder of HighliteMap
	(properties
		x 82
		y 81
		noun 5
		onMeCheck $0004
		view 132
		tpRoom 450
	)
)

(instance beast of HighliteMap
	(properties
		x 213
		y 92
		noun 2
		onMeCheck $0010
		view 132
		loop 1
		tpRoom 500
	)
)

(instance mists of HighliteMap
	(properties
		x 256
		y 142
		noun 3
		onMeCheck $0080
		view 132
		loop 2
		tpRoom 550
	)
	
	(method (init)
		(if (Btst 3) (mistsLabel init:) (mistsProp init:))
		(super init: &rest)
	)
	
	(method (doVerb theVerb &tmp temp0)
		(= temp0 (Btst 3))
		(cond 
			((and (== theVerb 5) temp0) (super doVerb: theVerb &rest))
			((and (== theVerb 1) temp0 (not (Btst 80))) (Bset 80) (messager say: noun theVerb 12 0 0 modNum))
			((and (== theVerb 1) temp0) (messager say: noun theVerb 11 0 0 modNum))
			(else (messager say: noun theVerb 0 0 0 modNum))
		)
	)
	
	(method (onMe)
		(if (Btst 3) (= noun 3) else (= noun 4))
		(super onMe: &rest)
	)
)

(instance northMarker of Feature
	(properties
		noun 8
		modNum 130
		onMeCheck $0020
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 5)
			(messager say: 6 theVerb 0 0 0 130)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance mapTitle of Feature
	(properties
		noun 7
		modNum 130
		onMeCheck $0040
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 5)
			(messager say: 6 theVerb 0 0 0 130)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance mistsLabel of Actor
	(properties
		x 207
		y 156
		noun 3
		view 132
		loop 3
	)
	
	(method (doVerb)
		(mists doVerb: &rest)
	)
)

(instance exitFeature of Feature
	(properties
		onMeCheck $0100
	)
	
	(method (doVerb)
		(MapScr resetHandlers: register: 1 cue:)
	)
)

(instance mapSound of Sound
	(properties)
)
