;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64891)
(include sci.sh)
(use Main)
(use List)
(use Set)
(use Plane)
(use Print)
(use Actor)
(use System)


(class ScrollView of View
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
		maxY 0
		minLoop 0
		maxLoop 0
		bAutoScroll 0
		bIsInitialized 0
		oWalkHandler 0
		setWalkHandler 0
	)
	
	(method (talkerList param1 param2 &tmp temp0 temp1 setWalkHandlerRelVolPercent setWalkHandlerAudTicks)
		(if setWalkHandler
			(if argc (= temp0 param1) else (= temp0 0))
			(if (> argc 1) (= temp1 param2) else (= temp1 0))
			(= setWalkHandlerRelVolPercent
				(setWalkHandler relVolPercent?)
			)
			(= setWalkHandlerAudTicks (setWalkHandler audTicks?))
			(= maxY (- setWalkHandlerRelVolPercent temp0))
			(= minLoop (- setWalkHandlerAudTicks temp1))
			(if (== temp0 0) (= maxLoop (= maxY 1)))
			(if (== temp1 0) (= bAutoScroll (= minLoop 1)))
		)
		(= bIsInitialized x)
		(= oWalkHandler y)
	)
	
	(method (bGameWasHandsOff param1 param2 param3 &tmp setWalkHandlerRelVolPercent setWalkHandlerAudTicks)
		(if (and setWalkHandler (> argc 2))
			(cond 
				((== param1 0)
					(= maxY
						(-
							(= setWalkHandlerRelVolPercent
								(setWalkHandler relVolPercent?)
							)
							(/ (* setWalkHandlerRelVolPercent param2) param3)
						)
					)
				)
				((== param1 1)
					(= minLoop
						(-
							(= setWalkHandlerAudTicks (setWalkHandler audTicks?))
							(/ (* setWalkHandlerAudTicks param2) param3)
						)
					)
				)
			)
		)
		(= bIsInitialized x)
		(= oWalkHandler y)
	)
	
	(method (bActive param1 param2 param3 &tmp temp0 setWalkHandlerRelVolPercent setWalkHandlerAudTicks setWalkHandlerSaveTime setWalkHandlerOSubtitle temp5 temp6 temp7 temp8)
		(if (> param2 param3)
			(= temp0 param2)
			(= param2 param3)
			(= param3 temp0)
		)
		(if (and setWalkHandler (> argc 1))
			(cond 
				((== param1 0)
					(= temp5 (CelWide view loop cel))
					(= setWalkHandlerRelVolPercent
						(setWalkHandler relVolPercent?)
					)
					(= setWalkHandlerSaveTime (setWalkHandler saveTime?))
					(= temp7 (- param3 param2))
					(= maxY
						(/
							(*
								setWalkHandlerRelVolPercent
								(= temp8
									(- (- param3 temp5) (+ param2 setWalkHandlerSaveTime))
								)
							)
							temp7
						)
					)
					(= maxY (>> maxY $0004))
					(= maxLoop (>> setWalkHandlerRelVolPercent $0004))
					(= x (- param3 (/ (* maxY param3) maxLoop)))
					(UpdateScreenItem self)
					(SetNowSeen self)
				)
				((== param1 1)
					(= temp6 (CelHigh view loop cel))
					(= setWalkHandlerAudTicks (setWalkHandler audTicks?))
					(= setWalkHandlerOSubtitle (setWalkHandler oSubtitle?))
					(= temp7 (- param3 param2))
					(= temp8
						(- (- param3 temp6) (+ param2 setWalkHandlerOSubtitle))
					)
					(= minLoop
						(- 0 (/ (* setWalkHandlerAudTicks temp8) temp7))
					)
					(= minLoop (>> minLoop $0004))
					(= bAutoScroll (>> setWalkHandlerAudTicks $0004))
					(= y (- param3 (/ (* minLoop param3) bAutoScroll)))
					(UpdateScreenItem self)
					(SetNowSeen self)
				)
			)
		)
		(= bIsInitialized x)
		(= oWalkHandler y)
	)
	
	(method (kill param1 param2 &tmp setWalkHandlerRelVolPercent setWalkHandlerAudTicks setWalkHandlerSaveTime setWalkHandlerOSubtitle)
		(if (and setWalkHandler (> argc 1))
			(cond 
				((== param1 0)
					(= setWalkHandlerRelVolPercent
						(setWalkHandler relVolPercent?)
					)
					(= setWalkHandlerSaveTime (setWalkHandler saveTime?))
					(= maxY
						(-
							setWalkHandlerRelVolPercent
							(- param2 setWalkHandlerSaveTime)
						)
					)
				)
				((== param1 1)
					(= setWalkHandlerAudTicks (setWalkHandler audTicks?))
					(= setWalkHandlerOSubtitle (setWalkHandler oSubtitle?))
					(= minLoop
						(-
							setWalkHandlerAudTicks
							(- param2 setWalkHandlerOSubtitle)
						)
					)
				)
			)
		)
		(= bIsInitialized x)
		(= oWalkHandler y)
	)
	
	(method (bInitialized theMaxY theMinLoop)
		(= maxY theMaxY)
		(= minLoop theMinLoop)
		(= bIsInitialized x)
		(= oWalkHandler y)
	)
	
	(method (curSFXVolume &tmp setWalkHandlerSetMusic setWalkHandlerAudTicks setWalkHandlerRelVolPercent)
		(if setWalkHandler
			(if maxY
				(= setWalkHandlerSetMusic (setWalkHandler setMusic?))
				(= setWalkHandlerRelVolPercent
					(setWalkHandler relVolPercent?)
				)
				(if (not maxLoop)
					(= maxLoop setWalkHandlerRelVolPercent)
				)
				(= x
					(+
						bIsInitialized
						(MulDiv maxY setWalkHandlerSetMusic maxLoop)
					)
				)
				(UpdateScreenItem self)
				(SetNowSeen self)
			)
			(if minLoop
				(= setWalkHandlerSetMusic (setWalkHandler setAmbient?))
				(= setWalkHandlerAudTicks (setWalkHandler audTicks?))
				(if (not bAutoScroll)
					(= bAutoScroll setWalkHandlerAudTicks)
				)
				(= y
					(+
						oWalkHandler
						(MulDiv minLoop setWalkHandlerSetMusic bAutoScroll)
					)
				)
				(UpdateScreenItem self)
				(SetNowSeen self)
			)
		)
	)
)

(class Panner of Code
	(properties
		scratch 0
		playSound 0
		setRelVol 0
		isPlaying 0
		preload 0
		playMessage 0
		playSubtitledSound 0
		fadeIn 0
		fadeOut 0
	)
	
	(method (init thePreload thePlayMessage theFadeOut thePlaySound theSetRelVol theIsPlaying thePlaySubtitledSound)
		(if (or (< argc 4) (not thePlaySound))
			(= playSound (ego moveSpeed?))
		else
			(= playSound thePlaySound)
		)
		(if (or (< argc 5) (not theSetRelVol))
			(= setRelVol (ego xStep?))
		else
			(= setRelVol theSetRelVol)
		)
		(if (or (< argc 6) (not theIsPlaying))
			(= isPlaying (ego yStep?))
		else
			(= isPlaying theIsPlaying)
		)
		(if (or (< argc 7) (not thePlaySubtitledSound))
			(= playSubtitledSound (curRoom plane?))
		else
			(= playSubtitledSound thePlaySubtitledSound)
		)
		(if (< argc 3)
			(= fadeOut 0)
		else
			(= fadeOut theFadeOut)
		)
		(if
			(or
				(< argc 2)
				(not playSubtitledSound)
				(not (playSubtitledSound isKindOf: ScrollPlane))
			)
			(Prints {Improper init of Panner. Scrlplan.sc djm})
		)
		(= preload thePreload)
		(= playMessage thePlayMessage)
		(if (== preload -32000)
			(= preload (playSubtitledSound setMusic?))
		)
		(if (< preload 0) (= preload 0))
		(if (> preload (playSubtitledSound relVolPercent?))
			(= preload (playSubtitledSound relVolPercent?))
		)
		(if (== playMessage -32000)
			(= playMessage (playSubtitledSound setAmbient?))
		)
		(if (< playMessage 0) (= playMessage 0))
		(if (> playMessage (playSubtitledSound audTicks?))
			(= playMessage (playSubtitledSound audTicks?))
		)
		(= fadeIn gameTime)
		(theDoits add: self)
	)
	
	(method (doit &tmp playSubtitledSoundSetMusic playSubtitledSoundSetAmbient temp2 temp3 temp4 temp5 temp6 [temp7 2] temp9 temp10)
		(if (== playSound 0) (= playSound 1))
		(if (>= (= temp6 (- gameTime fadeIn)) playSound)
			(= temp9 (/ (* setRelVol temp6) playSound))
			(= temp10 (/ (* isPlaying temp6) playSound))
			(= playSubtitledSoundSetMusic
				(playSubtitledSound setMusic?)
			)
			(= playSubtitledSoundSetAmbient
				(playSubtitledSound setAmbient?)
			)
			(if
				(and
					(== playSubtitledSoundSetMusic preload)
					(== playSubtitledSoundSetAmbient playMessage)
				)
				(theDoits delete: self)
				(if fadeOut (fadeOut cue:))
				(return)
			)
			(= temp2 (- preload playSubtitledSoundSetMusic))
			(if
				(<
					(= temp3 (- playMessage playSubtitledSoundSetAmbient))
					0
				)
				(= temp5 -1)
			else
				(= temp5 1)
			)
			(if (< temp2 0) (= temp4 -1) else (= temp4 1))
			(= temp2 (Abs temp2))
			(= temp3 (Abs temp3))
			(if (< temp2 temp9)
				(= playSubtitledSoundSetMusic
					(+ playSubtitledSoundSetMusic (* temp2 temp4))
				)
			else
				(= playSubtitledSoundSetMusic
					(+ playSubtitledSoundSetMusic (* temp9 temp4))
				)
			)
			(if (< temp3 temp10)
				(= playSubtitledSoundSetAmbient
					(+ playSubtitledSoundSetAmbient (* temp3 temp5))
				)
			else
				(= playSubtitledSoundSetAmbient
					(+ playSubtitledSoundSetAmbient (* temp10 temp5))
				)
			)
			(playSubtitledSound
				fadeRel: playSubtitledSoundSetMusic playSubtitledSoundSetAmbient
			)
			(= fadeIn gameTime)
		)
	)
	
	(method (dispose)
		(theDoits delete: self)
	)
)

(class ScrollPlane of Plane
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
	)
	
	(method (init param1 param2 &tmp temp0)
		(super init: &rest)
		(if (not saveTime)
			(= saveTime (thePlane findData:))
			(= oSubtitle (thePlane doDouble:))
			(= getCurVol (thePlane left:))
			(= reSyncVol (thePlane top?))
		)
		(self
			setRect:
				getCurVol
				reSyncVol
				(- (+ getCurVol param1) 1)
				(- (+ reSyncVol param2) 1)
		)
		(= relVolPercent (- param1 saveTime))
		(= audTicks (- param2 oSubtitle))
		(self nSeq: oText:)
		(if newSound
			(newSound eachElementDo: #init (self nSpeed:))
		)
		(= setMusic -1)
		(self fadeRel: 0 0)
		(= gVoMyHelp self)
	)
	
	(method (dispose)
		(= gVoMyHelp 0)
		(if newSound
			(newSound release:)
			(newSound dispose:)
			(= newSound 0)
		)
		(if bIsPaused
			(bIsPaused release:)
			(bIsPaused dispose:)
			(= bIsPaused 0)
		)
		(if nTimeFrozen
			(nTimeFrozen release:)
			(nTimeFrozen dispose:)
			(= nTimeFrozen 0)
		)
		(self bSpinning:)
		(super dispose: &rest)
	)
	
	(method (replay)
		(self nSeq:)
	)
	
	(method (nInitPlaneY param1)
		(if (not newSound) (= newSound (Set new:)))
		(newSound add: (param1 setWalkHandler: self yourself:))
	)
	
	(method (nInitCursorX param1 param2 &tmp temp0 temp1)
		(if argc (= temp0 param1) else (= temp0 0))
		(if (> argc 1) (= temp1 param2) else (= temp1 0))
		(self fadeRel: (+ temp0 setMusic) (+ temp1 setAmbient))
	)
	
	(method (fadeRel theTheRelVolPercent theTheAudTicks &tmp theRelVolPercent theAudTicks bIsPausedFirst nTimeFrozenFirst temp4 temp5 scrollPlaneFindData scrollPlaneDoDouble)
		(if argc
			(= theRelVolPercent theTheRelVolPercent)
		else
			(= theRelVolPercent 0)
		)
		(if (> argc 1)
			(= theAudTicks theTheAudTicks)
		else
			(= theAudTicks 0)
		)
		(if (> theRelVolPercent relVolPercent)
			(= theRelVolPercent relVolPercent)
		)
		(if (< theRelVolPercent 0) (= theRelVolPercent 0))
		(if (> theAudTicks audTicks) (= theAudTicks audTicks))
		(if (< theAudTicks 0) (= theAudTicks 0))
		(if
			(and
				(== setMusic theRelVolPercent)
				(== setAmbient theAudTicks)
			)
			(return)
		)
		(= setMusic theRelVolPercent)
		(= setAmbient theAudTicks)
		(= scrollPlaneFindData (self findData:))
		(= scrollPlaneDoDouble (self doDouble:))
		(= top (- reSyncVol setAmbient))
		(= left (- getCurVol setMusic))
		(self
			setRect:
				left
				top
				(- (+ left scrollPlaneFindData) 1)
				(- (+ top scrollPlaneDoDouble) 1)
		)
		(if newSound (newSound eachElementDo: #curSFXVolume))
		(UpdatePlane self)
		(if (or (not bIsPaused) (not nTimeFrozen)) (return))
		(= bIsPausedFirst (bIsPaused first:))
		(= nTimeFrozenFirst (nTimeFrozen first:))
		(while (and bIsPausedFirst nTimeFrozenFirst)
			(= temp4 (KList 8 bIsPausedFirst))
			(= temp5 (KList 8 nTimeFrozenFirst))
			(if (and temp4 temp5) (Eval temp4 temp5))
			(= bIsPausedFirst (bIsPaused next: bIsPausedFirst))
			(= nTimeFrozenFirst (bIsPaused next: nTimeFrozenFirst))
		)
	)
	
	(method (nInitCursorY param1 param2 &tmp temp0 temp1 temp2 temp3 temp4 temp5 temp6)
		(if (!= (param1 plane?) self)
			(Prints
				{Attempt to scroll to object not on our plane. DJM scrlplan.sc}
			)
		)
		(if (< argc 2) (= temp0 0) else (= temp0 param2))
		(if (and (self nVerb: param1) (not temp0)) (return))
		(= temp1 (/ saveTime 2))
		(= temp2 (/ oSubtitle 2))
		(param1 nScrollMaxY:)
		(= temp3
			(+
				(param1 nsLeft?)
				(/ (- (param1 nsRight?) (param1 nsLeft?)) 2)
			)
		)
		(= temp4
			(+
				(param1 nsTop?)
				(/ (- (param1 nsBottom?) (param1 nsTop?)) 2)
			)
		)
		(= temp3 (- temp3 setMusic))
		(= temp4 (- temp4 setAmbient))
		(= temp5 (- temp3 temp1))
		(= temp6 (- temp4 temp2))
		(self nInitCursorX: temp5 temp6)
	)
	
	(method (makeTopBorder param1 param2 param3 &tmp temp0 temp1 temp2 temp3 temp4)
		(if (< argc 2) (= temp0 0) else (= temp0 param3))
		(if (and (self nCase: param1 param2) (not temp0))
			(return)
		)
		(= temp1 (/ saveTime 2))
		(= temp2 (/ oSubtitle 2))
		(= param1 (- param1 setMusic))
		(= param2 (- param2 setAmbient))
		(= temp3 (- param1 temp1))
		(= temp4 (- param2 temp2))
		(self nInitCursorX: temp3 temp4)
	)
	
	(method (sitNSpin param1 param2 param3 theEgoMoveSpeed theEgoXStep theEgoYStep &tmp egoMoveSpeed egoXStep egoYStep temp3)
		(if (or (< argc 4) (not theEgoMoveSpeed))
			(= egoMoveSpeed (ego moveSpeed?))
		else
			(= egoMoveSpeed theEgoMoveSpeed)
		)
		(if (or (< argc 5) (not theEgoXStep))
			(= egoXStep (ego xStep?))
		else
			(= egoXStep theEgoXStep)
		)
		(if (or (< argc 6) (not theEgoYStep))
			(= egoYStep (ego yStep?))
		else
			(= egoYStep theEgoYStep)
		)
		(if (< argc 3) (= temp3 0) else (= temp3 param3))
		(if (< argc 2)
			(Prints {Improper call to panTo. Scrlplan.sc djm})
		)
		(self bSpinning:)
		(= nInitPlaneX
			((Panner new:)
				init: param1 param2 temp3 egoMoveSpeed egoXStep egoYStep self
				yourself:
			)
		)
	)
	
	(method (bSpinning)
		(if nInitPlaneX
			(nInitPlaneX dispose:)
			(= nInitPlaneX 0)
		)
	)
	
	(method (deleteRoomPlane param1 param2)
		(if (not bIsPaused)
			(= bIsPaused (List new:))
			(= nTimeFrozen (List new:))
		)
		(bIsPaused add: param1)
		(nTimeFrozen add: param2)
	)
	
	(method (addRoomPlane)
		(return (if (> setAmbient 0) (return 1) else (return 0)))
	)
	
	(method (oNotify)
		(return (if (< setAmbient audTicks) (return 1) else (return 0)))
	)
	
	(method (chShortcut)
		(return (if (> setMusic 0) (return 1) else (return 0)))
	)
	
	(method (nModifiers)
		(return
			(if (< setMusic relVolPercent)
				(return 1)
			else
				(return 0)
			)
		)
	)
	
	(method (nModule param1)
		(return (- (param1 x?) setMusic))
	)
	
	(method (nNoun param1)
		(return (- (param1 y?) setAmbient))
	)
	
	(method (nVerb param1)
		(if (< (- (param1 nsLeft?) setMusic) 0) (return 0))
		(if (< (- (param1 nsTop?) setAmbient) 0) (return 0))
		(if (> (- (param1 nsRight?) setMusic) saveTime)
			(return 0)
		)
		(if
		(> (- (param1 nsBottom?) setAmbient) oSubtitle)
			(return 0)
		)
		(return 1)
	)
	
	(method (nCase param1 param2)
		(if (< (- param1 setMusic) 0) (return 0))
		(if (< (- param2 setAmbient) 0) (return 0))
		(if (> (- param1 setMusic) saveTime) (return 0))
		(if (> (- param2 setAmbient) oSubtitle) (return 0))
		(return 1)
	)
	
	(method (nSeq)
	)
	
	(method (oText)
	)
)
