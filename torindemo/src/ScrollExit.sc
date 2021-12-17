;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64009)
(include sci.sh)
(use Main)
(use ScrollView)
(use Plane)
(use Print)
(use PolyPath)
(use Feature)
(use Actor)


(class ScrollExit of Feature
	(properties
		scratch 0
		heading 0
		noun 0
		case 0
		modNum -1
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
		sightAngle 26505
		actions 0
		onMeCheck $0000
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		plane 0
		x 0
		y 0
		z 0
		killToolHelp 1
		handlePlaneEvent 30
		addUnderMouse 40
		voMyHelp 0
		nGameSpeed 0
		autosave 0
		autorestore 0
	)
	
	(method (init)
		(super init: &rest)
		(= voMyHelp gVoMyHelp)
		(switch killToolHelp
			(3
				(= nGameSpeed (ScriptID 64006 2))
				(= nsTop (- (plane bottom?) handlePlaneEvent))
				(= nsBottom (plane bottom?))
				(= nsRight (plane right:))
				(= nsLeft 0)
				(= autosave (voMyHelp left:))
				(= autorestore (- (voMyHelp doDouble:) addUnderMouse))
			)
			(2
				(= nGameSpeed (ScriptID 64006 3))
				(= nsTop 0)
				(= nsBottom (plane bottom?))
				(= nsRight (plane right:))
				(= nsLeft (- (plane right:) handlePlaneEvent))
				(= autosave (- (voMyHelp findData:) addUnderMouse))
				(= autorestore (voMyHelp top?))
			)
			(4
				(= nGameSpeed (ScriptID 64006 4))
				(= nsTop 0)
				(= nsBottom (plane bottom?))
				(= nsRight handlePlaneEvent)
				(= nsLeft 0)
				(= autosave addUnderMouse)
				(= autorestore (voMyHelp top?))
			)
			(else 
				(= nGameSpeed (ScriptID 64006 1))
				(= nsTop 0)
				(= nsBottom handlePlaneEvent)
				(= nsRight (plane right:))
				(= nsLeft 0)
				(= autosave (voMyHelp left:))
				(= autorestore addUnderMouse)
			)
		)
		(self setSpeedFraction: nGameSpeed)
	)
	
	(method (show)
		(self setSpeedFraction: nGameSpeed)
	)
	
	(method (handleEvent event)
		(return
			(if
				(or
					(not ego)
					(not (ego scratch?))
					(not (ego isNotHidden:))
				)
				(return 0)
			else
				(return (super handleEvent: event &rest))
			)
		)
	)
	
	(method (doVerb &tmp theAutosave theAutorestore)
		(if
			(or
				(not ego)
				(not (ego scratch?))
				(not (ego isNotHidden:))
			)
			(return)
		)
		(switch killToolHelp
			(3
				(= theAutosave (- mouseX autosave))
				(= theAutorestore autorestore)
			)
			(2
				(= theAutosave autosave)
				(= theAutorestore (- mouseY autorestore))
			)
			(4
				(= theAutosave autosave)
				(= theAutorestore (- mouseY autorestore))
			)
			(else 
				(= theAutosave (- mouseX autosave))
				(= theAutorestore autorestore)
			)
		)
		(if (ego vCheck?)
			((ego vCheck?) doit: theAutosave theAutorestore)
		else
			(ego setMotion: PolyPath theAutosave theAutorestore)
		)
	)
	
	(method (hide)
		(self setSpeedFraction: 0)
	)
)

(instance foNScroll of ScrollExit
	(properties)
)

(instance foSScroll of ScrollExit
	(properties
		killToolHelp 3
	)
)

(instance foWScroll of ScrollExit
	(properties
		killToolHelp 4
	)
)

(instance foEScroll of ScrollExit
	(properties
		killToolHelp 2
	)
)

(class TorScrollPlane of ScrollPlane
	(properties
		scratch 0
		resX -1
		resY -1
		left 0
		top 0
		right 0
		bottom 0
		inLeft 0
		inTop 0
		inRight 0
		inBottom 0
		vanishingX 0
		vanishingY 0
		coordType 0
		picture -1
		style $0000
		priority -1
		back 0
		bitmap 0
		casts 0
		mirrored 0
		nScreenSizeX 0
		saveTime 0
		oSubtitle 0
		getCurVol 0
		reSyncVol 0
		setMusic 0
		setAmbient 0
		relVolPercent 0
		audTicks 0
		newSound 0
		bIsPaused 0
		nTimeFrozen 0
		nInitPlaneX 0
		oBoogleFeatures -1
		oExtraPlanes -1
		oMainPlane -1
		addBoogleFeature -1
		delBoogleFeature -1
		showHelp -1
	)
	
	(method (init)
		(super init: &rest)
		(if relVolPercent
			(if (== -1 oMainPlane)
				(= oMainPlane
					(foEScroll init: (ScriptID 0 1) yourself:)
				)
			)
			(if (== -1 addBoogleFeature)
				(= addBoogleFeature
					(foWScroll init: (ScriptID 0 1) yourself:)
				)
			)
			(if (== -1 delBoogleFeature)
				(= delBoogleFeature
					(oHHandle gimme: self init: yourself:)
				)
			)
		)
		(if audTicks
			(if (== -1 oBoogleFeatures)
				(= oBoogleFeatures
					(foNScroll init: (ScriptID 0 1) yourself:)
				)
			)
			(if (== -1 oExtraPlanes)
				(= oExtraPlanes
					(foSScroll init: (ScriptID 0 1) yourself:)
				)
			)
			(if (== -1 showHelp)
				(= showHelp (oVHandle gimme: self init: yourself:))
			)
		)
	)
	
	(method (dispose)
		(if (and delBoogleFeature (!= -1 delBoogleFeature))
			(delBoogleFeature dispose:)
		)
		(if (and showHelp (!= -1 showHelp))
			(showHelp dispose:)
		)
		(if (and oBoogleFeatures (!= -1 oBoogleFeatures))
			(oBoogleFeatures dispose:)
		)
		(if (and oExtraPlanes (!= -1 oExtraPlanes))
			(oExtraPlanes dispose:)
		)
		(if (and oMainPlane (!= -1 oMainPlane))
			(oMainPlane dispose:)
		)
		(if (and addBoogleFeature (!= -1 addBoogleFeature))
			(addBoogleFeature dispose:)
		)
		(= delBoogleFeature
			(= showHelp
				(= oBoogleFeatures
					(= oExtraPlanes (= oMainPlane (= addBoogleFeature -1)))
				)
			)
		)
		(super dispose: &rest)
	)
	
	(method (enable)
		(super enable: &rest)
		(if (and delBoogleFeature (!= -1 delBoogleFeature))
			(delBoogleFeature show:)
		)
		(if (and showHelp (!= -1 showHelp)) (showHelp show:))
		(if (and oBoogleFeatures (!= -1 oBoogleFeatures))
			(oBoogleFeatures show:)
		)
		(if (and oExtraPlanes (!= -1 oExtraPlanes))
			(oExtraPlanes show:)
		)
		(if (and oMainPlane (!= -1 oMainPlane))
			(oMainPlane show:)
		)
		(if (and addBoogleFeature (!= -1 addBoogleFeature))
			(addBoogleFeature show:)
		)
	)
	
	(method (disable)
		(if (and delBoogleFeature (!= -1 delBoogleFeature))
			(delBoogleFeature hide:)
		)
		(if (and showHelp (!= -1 showHelp)) (showHelp hide:))
		(if (and oBoogleFeatures (!= -1 oBoogleFeatures))
			(oBoogleFeatures hide:)
		)
		(if (and oExtraPlanes (!= -1 oExtraPlanes))
			(oExtraPlanes hide:)
		)
		(if (and oMainPlane (!= -1 oMainPlane))
			(oMainPlane hide:)
		)
		(if (and addBoogleFeature (!= -1 addBoogleFeature))
			(addBoogleFeature hide:)
		)
		(super disable: &rest)
	)
	
	(method (sitNSpin)
		(ego bMouseDown: 0)
		(super sitNSpin: &rest)
	)
)

(class TorScrollHandle of View
	(properties
		scratch 0
		heading 0
		noun 0
		case 0
		modNum -1
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
		sightAngle 26505
		actions 0
		onMeCheck $0000
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		plane 0
		x 0
		y 0
		z 0
		scaleX 128
		scaleY 128
		maxScale 128
		scaleType 0
		priority 0
		fixPriority 0
		inLeft 0
		inTop 0
		inRight 0
		inBottom 0
		useInsetRect 0
		view -1
		loop 0
		cel 0
		bitmap 0
		yStep 2
		signal $4021
		lsLeft 0
		lsTop 0
		lsRight 0
		lsBottom 0
		brLeft 0
		brTop 0
		brRight 0
		brBottom 0
		scaleSignal $0000
		magnifier 0
		oldScaleX 128
		gimme 0
		currentX 0
		currentY 0
		shortestDistance 1
		invSlotsX 0
		lines 0
		findClosestPoint 0
		insertPoint 0
		deletePoint 0
	)
	
	(method (init &tmp newPlane)
		((= newPlane (Plane new:))
			picture: -2
			priority: 420
			init:
		)
		(super init: (newPlane nSpeed:) &rest)
		(self setSpeedFraction: (ScriptID 64006 0))
		(newPlane setSize:)
		(newPlane
			moveTo: (- (thePlane right:) 6) (- (thePlane bottom?) 6)
		)
		(gimme deleteRoomPlane: self 1116)
		(self indexToCoor:)
	)
	
	(method (dispose &tmp thePlane_2)
		(= thePlane_2 plane)
		(super dispose: &rest)
		(thePlane_2 dispose:)
		(= gimme (= plane 0))
	)
	
	(method (handleEvent event &tmp theCurrentX gimmeRelVolPercent temp2 temp3)
		(if (not gimme)
			(return
				(Prints
					{Attempt to handleEvent scroll handle with no scroll plane. DJM torscrol.sc}
				)
			)
		)
		(if
			(and
				(self onMe: event)
				(== (event type?) evMOUSEBUTTON)
			)
			(= invSlotsX 1)
			(gOEventHandler screenLocY: self)
			(event globalize:)
			(ego bMouseDown: 0)
			(= lines (event x?))
			(= findClosestPoint (event y?))
			(= insertPoint (plane left:))
			(= deletePoint (plane top?))
		)
		(if invSlotsX
			(if (== (event type?) evMOUSERELEASE)
				(= invSlotsX 0)
				(gOEventHandler screenLocX: self)
			)
			(event globalize:)
			(if shortestDistance
				(if
					(<
						(= theCurrentX (+ (- (event x?) lines) insertPoint))
						currentX
					)
					(= theCurrentX currentX)
				)
				(if (> theCurrentX currentY) (= theCurrentX currentY))
				(plane moveTo: theCurrentX deletePoint)
				(UpdatePlane plane)
				(= temp2 (- currentY currentX))
				(= theCurrentX (- theCurrentX currentX))
				(= gimmeRelVolPercent (gimme relVolPercent?))
				(= temp3 (MulDiv theCurrentX gimmeRelVolPercent temp2))
				(gimme
					fadeRel:
						(MulDiv theCurrentX gimmeRelVolPercent temp2)
						(gimme setAmbient?)
				)
			else
				(if
					(<
						(= theCurrentX
							(+ (- (event y?) findClosestPoint) deletePoint)
						)
						currentX
					)
					(= theCurrentX currentX)
				)
				(if (> theCurrentX currentY) (= theCurrentX currentY))
				(plane moveTo: insertPoint theCurrentX)
				(UpdatePlane plane)
				(= temp2 (- currentY currentX))
				(= theCurrentX (- theCurrentX currentX))
				(= gimmeRelVolPercent (gimme audTicks?))
				(gimme
					fadeRel: (gimme setMusic?) (MulDiv theCurrentX gimmeRelVolPercent temp2)
				)
			)
			(event claimed: 1)
			(return 1)
		)
		(return (super handleEvent: event &rest))
	)
	
	(method (indexToCoor &tmp gimmeSetMusic gimmeRelVolPercent temp2)
		(if (not gimme)
			(Prints
				{Attempt to resynch scroll handle with no scroll plane. DJM torscrol.sc}
			)
			(return)
		)
		(= temp2 (- currentY currentX))
		(if shortestDistance
			(= gimmeSetMusic (gimme setMusic?))
			(= gimmeRelVolPercent (gimme relVolPercent?))
			(plane
				moveTo:
					(+
						(MulDiv gimmeSetMusic temp2 gimmeRelVolPercent)
						currentX
					)
					(plane top?)
			)
			(UpdatePlane plane)
		else
			(= gimmeSetMusic (gimme setAmbient?))
			(= gimmeRelVolPercent (gimme audTicks?))
			(plane
				moveTo:
					(plane left:)
					(+
						(MulDiv gimmeSetMusic temp2 gimmeRelVolPercent)
						currentX
					)
			)
			(UpdatePlane plane)
		)
	)
)

(instance oHHandle of TorScrollHandle
	(properties
		view -5532
		loop 18
		currentX 20
		currentY 620
	)
	
	(method (init)
		(super init: &rest)
		(= currentX (+ (thePlane left:) 10))
		(= currentY
			(- (thePlane right:) (+ 10 (CelWide view loop cel)))
		)
	)
)

(instance oVHandle of TorScrollHandle
	(properties
		view -5532
		loop 19
		currentX 24
		currentY 297
		shortestDistance 0
	)
	
	(method (init)
		(super init: &rest)
		(= currentX (+ (thePlane top?) 12))
		(= currentY
			(- (thePlane bottom?) (+ 12 (CelHigh view loop cel)))
		)
	)
)
