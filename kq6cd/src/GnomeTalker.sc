;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1037)
(include sci.sh)
(use Main)
(use Kq6Talker)
(use Kq6Procs)
(use Actor)
(use System)

(public
	Gnomes 36
	GSmell 61
	GSound 65
	GTaste 66
	GTouch 67
	GSight 68
)

(class GnomeTalker of Kq6Talker
	(properties
		x -1
		y -1
		z 0
		heading 0
		noun 0
		modNum -1
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
		yStep 2
		view -1
		loop 0
		cel 0
		priority 0
		underBits 0
		signal $0000
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		scaleSignal $0000
		scaleX 128
		scaleY 128
		maxScale 128
		cycleSpeed 6
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
		caller 0
		disposeWhenDone 2
		ticks 0
		talkWidth 270
		keepWindow 0
		modeless 0
		font 0
		cueVal 0
		initialized 0
		showTitle 0
		color 0
		back 7
		curVolume 0
		saveCursor 0
		bust 0
		eyes 0
		mouth 0
		viewInPrint 0
		textX 82
		textY 0
		useFrame 0
		blinkSpeed 100
		raving 0
		raveName 0
		saveX 0
		saveY 0
		winPosn 1
		extra 0
	)
	
	(method (init theBust theEyes theMouth theExtra)
		(if argc
			(= bust theBust)
			(if (>= argc 2)
				(= eyes theEyes)
				(if (>= argc 3)
					(= mouth theMouth)
					(if (and raving (>= argc 4)) (= extra theExtra))
				)
			)
		)
		(if (and (== msgType 2) argc (IsObject theBust))
			(self x: 59 y: 15)
		)
		(self setSize:)
		(super init:)
	)
	
	(method (doit)
		(if (and raving extra)
			(DrawCel
				(extra view?)
				(extra loop?)
				(extra cel?)
				(+ (extra nsLeft?) nsLeft)
				(+ (extra nsTop?) nsTop)
				-1
			)
		)
		(super doit:)
	)
	
	(method (show &tmp temp0)
		(if (not underBits)
			(= underBits
				(Graph grSAVE_BOX nsTop nsLeft nsBottom nsRight 1)
			)
		)
		(= temp0 (PicNotValid))
		(PicNotValid 1)
		(if bust
			(DrawCel
				(bust view?)
				(bust loop?)
				(bust cel?)
				(+ (bust nsLeft?) nsLeft)
				(+ (bust nsTop?) nsTop)
				-1
			)
		)
		(if eyes
			(DrawCel
				(eyes view?)
				(eyes loop?)
				(eyes cel?)
				(+ (eyes nsLeft?) nsLeft)
				(+ (eyes nsTop?) nsTop)
				-1
			)
		)
		(if mouth
			(DrawCel
				(mouth view?)
				(mouth loop?)
				(mouth cel?)
				(+ (mouth nsLeft?) nsLeft)
				(+ (mouth nsTop?) nsTop)
				-1
			)
		)
		(DrawCel view loop cel nsLeft nsTop -1)
		(if (and extra raving)
			(DrawCel
				(extra view?)
				(extra loop?)
				(extra cel?)
				(+ (extra nsLeft?) nsLeft)
				(+ (extra nsTop?) nsTop)
				-1
			)
		)
		(Graph grUPDATE_BOX nsTop nsLeft nsBottom nsRight 1)
		(PicNotValid temp0)
	)
	
	(method (setSize)
		(= nsLeft x)
		(= nsTop y)
		(= nsRight
			(+
				nsLeft
				(Max
					(if view (CelWide view loop cel) else 0)
					(if (IsObject bust)
						(+
							(bust nsLeft?)
							(CelWide (bust view?) (bust loop?) (bust cel?))
						)
					else
						0
					)
					(if (IsObject eyes)
						(+
							(eyes nsLeft?)
							(CelWide (eyes view?) (eyes loop?) (eyes cel?))
						)
					else
						0
					)
					(if (IsObject mouth)
						(+
							(mouth nsLeft?)
							(CelWide (mouth view?) (mouth loop?) (mouth cel?))
						)
					else
						0
					)
					(if (IsObject extra)
						(+
							(extra nsLeft?)
							(CelWide (extra view?) (extra loop?) (extra cel?))
						)
					else
						0
					)
				)
			)
		)
		(= nsBottom
			(+
				nsTop
				(Max
					(if view (CelHigh view loop cel) else 0)
					(if (IsObject bust)
						(+
							(bust nsTop?)
							(CelHigh (bust view?) (bust loop?) (bust cel?))
						)
					else
						0
					)
					(if (IsObject eyes)
						(+
							(eyes nsTop?)
							(CelHigh (eyes view?) (eyes loop?) (eyes cel?))
						)
					else
						0
					)
					(if (IsObject mouth)
						(+
							(mouth nsTop?)
							(CelHigh (mouth view?) (mouth loop?) (mouth cel?))
						)
					else
						0
					)
					(if (IsObject extra)
						(+
							(extra nsTop?)
							(CelHigh (extra view?) (extra loop?) (extra cel?))
						)
					else
						0
					)
				)
			)
		)
	)
)

(instance Gnomes of GnomeTalker
	(properties
		x 3
		y 5
		view 8930
		talkWidth 208
		textX 86
		textY 6
		raveName {GNOMES}
	)
	
	(method (init)
		(super init: tBust 0 tMouth &rest)
	)
)

(instance GSmell of GnomeTalker
	(properties
		x 3
		y 5
		view 8931
		talkWidth 208
		textX 86
		textY 8
		raveName {SMELL}
	)
	
	(method (init)
		(if (Btst 59)
			(super init: tSmellBust 0 tSmellMouth &rest)
		else
			(super init: tSmellBust 0 tSmellMouth tSmellPin &rest)
		)
	)
)

(instance GSound of GnomeTalker
	(properties
		x 3
		y 5
		view 8932
		talkWidth 208
		textX 86
		textY 8
		raveName {SOUND}
	)
	
	(method (init)
		(if (Btst 59)
			(super init: tSoundBust 0 tSoundMouth &rest)
		else
			(super init: tSoundBust 0 tSoundMouth tSoundMuffs &rest)
		)
	)
)

(instance GTaste of GnomeTalker
	(properties
		x 3
		y 5
		view 8933
		talkWidth 208
		textX 86
		textY 8
		raveName {TASTE}
	)
	
	(method (init)
		(super init: tTasteBust 0 tTasteMouth &rest)
	)
)

(instance GTouch of GnomeTalker
	(properties
		x 3
		y 5
		view 8934
		talkWidth 208
		textX 86
		textY 8
		raveName {TOUCH}
	)
	
	(method (init)
		(if (Btst 59)
			(super init: tTouchBust 0 tTouchMouth &rest)
		else
			(super init: tTouchBust 0 tTouchMouth tTouchGloves &rest)
		)
	)
)

(instance GSight of GnomeTalker
	(properties
		x 3
		y 5
		view 8935
		talkWidth 208
		textX 86
		textY 8
		raveName {SIGHT}
	)
	
	(method (init)
		(if (Btst 59)
			(super init: tSightBust tSightEyes tSightMouth 0 &rest)
		else
			(super init: tSightBust tSightLids tSightMouth 0 &rest)
		)
	)
)

(instance tSmellBust of Prop
	(properties
		view 8931
	)
)

(instance tSmellEyes of Prop
	(properties
		nsTop 15
		nsLeft 12
		view 8931
		loop 1
	)
)

(instance tSmellMouth of Prop
	(properties
		nsTop 36
		nsLeft 21
		view 8931
		loop 1
	)
)

(instance tSmellPin of Prop
	(properties
		nsTop 15
		nsLeft 26
		view 8931
		loop 3
	)
)

(instance tSoundBust of Prop
	(properties
		view 8932
	)
)

(instance tSoundEyes of Prop
	(properties
		nsTop 15
		nsLeft 20
		view 8932
		loop 1
	)
)

(instance tSoundMouth of Prop
	(properties
		nsTop 29
		nsLeft 31
		view 8932
		loop 1
	)
)

(instance tSoundMuffs of Prop
	(properties
		nsTop 20
		nsLeft 19
		view 8932
		loop 3
	)
)

(instance tTasteBust of Prop
	(properties
		view 8933
	)
)

(instance tTasteEyes of Prop
	(properties
		nsTop 8
		nsLeft 20
		view 8933
		loop 1
	)
)

(instance tTasteMouth of Prop
	(properties
		nsTop 20
		nsLeft 9
		view 8933
		loop 1
	)
)

(instance tTouchBust of Prop
	(properties
		view 8934
	)
)

(instance tTouchEyes of Prop
	(properties
		nsTop 2
		nsLeft 16
		view 8934
		loop 1
	)
)

(instance tTouchMouth of Prop
	(properties
		nsTop 16
		nsLeft 23
		view 8934
		loop 1
	)
)

(instance tTouchGloves of Prop
	(properties
		nsTop 28
		nsLeft 8
		view 8934
		loop 3
	)
)

(instance tSightBust of Prop
	(properties
		view 8935
	)
)

(instance tSightEyes of Prop
	(properties
		nsTop 20
		nsLeft 22
		view 8935
		loop 1
	)
)

(instance tSightLids of Prop
	(properties
		nsTop 19
		nsLeft 21
		view 8935
		loop 3
	)
)

(instance tSightMouth of Prop
	(properties
		nsTop 30
		nsLeft 33
		view 8935
		loop 2
	)
)

(instance tBust of Prop
	(properties
		view 8930
	)
)

(instance tMouth of Prop
	(properties
		nsTop 22
		nsLeft 11
		view 8930
		loop 1
	)
)
