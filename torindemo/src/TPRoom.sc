;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64020)
(include sci.sh)
(use Main)
(use FRButton)
(use ClickThrough)
(use Set)
(use CueMe)
(use ScrollView)
(use Print)
(use Game)
(use Actor)

(public
	oHandsOnWhenCued 0
)

(class TPRoom of Room
	(properties
		scratch 0
		script 0
		number 0
		modNum -1
		noun 0
		case 0
		timer 0
		keep 0
		initialized 0
		picture -1
		plane 0
		style $ffff
		exitStyle -1
		horizon 0
		controls 0
		north 0
		east 0
		south 0
		west 0
		curPic 0
		purge 500
		picAngle 0
		vanishingX 160
		vanishingY 0
		obstacles 0
		inset 0
		edgeN 40
		edgeE 319
		edgeW 0
		edgeS 189
		checkVerbHandlers 0
		poMyEgo 0
		poMyEgoView 0
	)
	
	(method (init)
		(super init: &rest)
		(Palette palSET_FROM_RESOURCE 999)
		(switch global202
			(1
				(Palette palSET_FROM_RESOURCE 10000)
			)
			(2)
			(3
				(Palette palSET_FROM_RESOURCE 30000)
			)
			(4
				(Palette palSET_FROM_RESOURCE -25536)
			)
			(5
				(Palette palSET_FROM_RESOURCE -15536)
			)
		)
		(RemapColors 2 238 25)
		(RemapColors 2 237 45)
		(RemapColors 2 236 75)
		(RemapColors 2 240 150)
	)
	
	(method (dispose &tmp temp0)
		(if checkVerbHandlers
			(checkVerbHandlers dispose:)
			(= checkVerbHandlers 0)
		)
		(if poMyEgo
			(while (poMyEgo size:)
				(= temp0 (poMyEgo at: 0))
				(self arrowDown: temp0)
			)
			(poMyEgo dispose:)
			(= poMyEgo 0)
		)
		((ScriptID 64002 4) disable: setScript: 0)
		((ScriptID 64002 6) disable: setScript: 0)
		(super dispose: &rest)
	)
	
	(method (setDefault)
		(if (not checkVerbHandlers)
			(= checkVerbHandlers (Set new:))
		)
		(checkVerbHandlers add: &rest)
	)
	
	(method (newTarget)
		(if checkVerbHandlers
			(checkVerbHandlers delete: &rest)
			(if (== (checkVerbHandlers size:) 0)
				(checkVerbHandlers dispose:)
				(= checkVerbHandlers 0)
			)
		)
	)
	
	(method (initThumb param1 &tmp thePlane_2 planePriority)
		(if (or (not argc) (not param1))
			(Prints {improper call of addRoomPlane. tproom.sc djm})
			(return)
		)
		(= thePlane_2 plane)
		(= planePriority (plane priority?))
		(curRoom plane: param1)
		(if (not poMyEgo) (= poMyEgo (Set new:)))
		(poMyEgo addToEnd: param1)
		(if (not poMyEgoView) (= poMyEgoView thePlane_2))
		(thePlane_2 disable:)
		(param1 init: setPri: (++ planePriority))
	)
	
	(method (arrowDown param1)
		(if
			(or
				(not argc)
				(not param1)
				(not poMyEgo)
				(not poMyEgoView)
			)
			(Prints {improper call of deleteRoomPlane. tproom.sc djm})
			(return)
		)
		(poMyEgo delete: param1)
		(if (== param1 plane)
			(if (poMyEgo size:)
				(= plane (KList 8 (poMyEgo last:)))
			else
				(= plane poMyEgoView)
				(= poMyEgoView 0)
			)
			(plane enable:)
		)
		(param1 dispose:)
	)
	
	(method (setArgs param1 &tmp planesNextNode temp1 temp2 temp3 temp4 temp5)
		(= planesNextNode (KList 3 (planes elements?)))
		(while planesNextNode
			(planes nextNode: (KList 6 planesNextNode))
			(= temp3 (KList 8 planesNextNode))
			(= temp1 (KList 3 ((temp3 casts?) elements?)))
			(while temp1
				((temp3 casts?) nextNode: (KList 6 temp1))
				(= temp4 (KList 8 temp1))
				(= temp2 (KList 3 (temp4 elements?)))
				(while temp2
					(temp4 nextNode: (KList 6 temp2))
					(= temp5 (KList 8 temp2))
					(if (not (KString 7 (temp5 name?) param1))
						(return temp5)
					)
					(= temp2 (temp4 nextNode?))
				)
				(= temp1 ((temp3 casts?) nextNode?))
			)
			(= temp4 (temp3 nScreenSizeX?))
			(= temp2 (KList 3 (temp4 elements?)))
			(while temp2
				(temp4 nextNode: (KList 6 temp2))
				(= temp5 (KList 8 temp2))
				(if (not (KString 7 (temp5 name?) param1))
					(return temp5)
				)
				(= temp2 (temp4 nextNode?))
			)
			(= planesNextNode (planes nextNode?))
		)
		(MonoOut {No object with name %s} param1)
		(return 0)
	)
	
	(method (highlight param1 param2 &tmp [temp0 3] temp3 temp4 temp5)
		(if (not param1) (return))
		(if (and (> argc 1) (!= param2 gVerb))
			(if gInventItem (gInventItem moveTo: -3))
			(cond 
				(
				(= temp3 ((ScriptID 64001 0) addItems: param2)) (proc64002_7) (temp3 moveTo: -2))
				(
				(= temp3 ((ScriptID 64001 1) addItems: param2)) (proc64002_8) (temp3 moveTo: -2))
			)
		)
		((ScriptID 64006 9) init:)
		(if (param1 isKindOf: View)
			(SetNowSeen param1)
		else
			(param1 nScrollMaxY:)
		)
		((ScriptID 64006 9)
			posn:
				(/ (+ (param1 nsLeft?) (param1 nsRight?)) 2)
				(/ (+ (param1 nsTop?) (param1 nsBottom?)) 2)
		)
		(if
			(and
				((curRoom plane?) isKindOf: ScrollPlane)
				(== (curRoom plane?) (param1 plane?))
			)
			(ego bMouseDown: 0)
			((curRoom plane?) nInitCursorY: param1)
		)
		(= temp4 (+ ((ScriptID 64006 9) x?) (plane left:)))
		(= temp5 (+ ((ScriptID 64006 9) y?) (plane top?)))
		(cond 
			((param1 setSpeedFraction:)
				((ScriptID 64006 9)
					loop: ((param1 setSpeedFraction:) loop?)
				)
			)
			((and (< temp4 100) (> temp5 240)) ((ScriptID 64006 9) loop: ((ScriptID 64006 8) loop?)))
			((and (>= temp4 100) (> temp5 240)) ((ScriptID 64006 9) loop: ((ScriptID 64006 7) loop?)))
			((and (< temp4 100) (<= temp5 240)) ((ScriptID 64006 9) loop: ((ScriptID 64006 6) loop?)))
			((and (>= temp4 100) (<= temp5 240)) ((ScriptID 64006 9) loop: ((ScriptID 64006 5) loop?)))
		)
	)
	
	(method (setWander param1)
		(MonoOut {You need to go to Room# %d} param1)
		(return 0)
	)
	
	(method (zipTo)
		(Help)
	)
	
	(method (intoPouch)
	)
)

(instance oHandsOnWhenCued of CueMe
	(properties)
	
	(method (cue)
		(theGame handsOn:)
	)
)
