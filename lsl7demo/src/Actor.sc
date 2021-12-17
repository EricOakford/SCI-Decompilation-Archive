;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64998)
(include game.sh)
(use Main)
(use CueMe)
(use Plane)
(use Array)
(use Print)
(use Scaler)
(use PolyPath)
(use Feature)
(use Grooper)
(use Motion)
(use System)


(class View of Feature
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
		signal $5021
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
	)
	
	(method (init param1 &tmp temp0)
		(= temp0
			(cond 
				(argc
					(if (param1 isKindOf: Plane)
						(param1 getMainCast:)
					else
						param1
					)
				)
				((and curRoom (curRoom plane?)) ((curRoom plane?) getMainCast:))
				(else cast)
			)
		)
		(= signal (& signal $fff7))
		(temp0 add: self)
		(= plane (temp0 plane?))
		(AddScreenItem self)
		(= -info- (| -info- $0010))
		(if (not (temp0 contains: self))
			(= lsBottom (= lsRight (= lsTop (= lsLeft 0))))
			(= signal (& signal $fff7))
		)
		(= useInsetRect 0)
		(if (and (== view -1) bitmap)
			(= signal (& signal $ffdf))
		)
		(if (& signal $0020) (BaseSetter self))
		(if (!= view -1) (SetNowSeen self))
		(self initialize: checkDetail:)
	)
	
	(method (doit)
		(if (and (& -info- $0008) (self isNotHidden:))
			(UpdateScreenItem self)
		)
	)
	
	(method (dispose &tmp planeCasts)
		(cast delete: self)
		(if (self isNotHidden:)
			(DeleteScreenItem self)
			(= -info- (& -info- $ffef))
		)
		(if plane
			((= planeCasts (plane casts?))
				eachElementDo: #delete self
			)
			(= plane 0)
		)
		(super dispose:)
	)
	
	(method (show)
		(if (not (self isNotHidden:))
			(= signal (& signal $fff7))
			(AddScreenItem self)
			(= -info- (| -info- $0010))
		)
	)
	
	(method (isNotHidden)
		(return (not (& signal $0008)))
	)
	
	(method (onMe theObjOrX theY &tmp theObjOrXX theObjOrXY)
		(if (== argc 1)
			(= theObjOrXX (theObjOrX x?))
			(= theObjOrXY (theObjOrX y?))
		else
			(= theObjOrXX theObjOrX)
			(= theObjOrXY theY)
		)
		(cond 
			((& signal $0008) 0)
			((not onMeCheck) (OnMe theObjOrXX theObjOrXY self (& signal $1000)))
			(else (super onMe: theObjOrXX theObjOrXY))
		)
	)
	
	(method (posn theX theY theZ)
		(if (>= argc 1)
			(= x theX)
			(if (>= argc 2)
				(= y theY)
				(if (>= argc 3) (= z theZ))
			)
		)
		(if (& signal $0020) (BaseSetter self))
	)
	
	(method (setPri thePriority)
		(cond 
			((== argc 0) (= fixPriority 1))
			((== thePriority -1) (= fixPriority 0))
			(else (= priority thePriority) (= fixPriority 1))
		)
	)
	
	(method (setLoop theLoop param2)
		(cond 
			((== argc 0) (= signal (| signal $0800)))
			((== theLoop -1) (= signal (& signal $f7ff)))
			(else
				(= loop theLoop)
				(if (> argc 1)
					(if param2
						(= signal (| signal $0800))
					else
						(= signal (& signal $f7ff))
					)
				)
			)
		)
	)
	
	(method (setCel theCel)
		(= cel theCel)
	)
	
	(method (setInsetRect theInLeft theInTop theInRight theInBottom)
		(if (< argc 4)
			(= useInsetRect 0)
			(= inLeft nsLeft)
			(= inTop nsTop)
			(= inRight nsRight)
			(= inBottom nsBottom)
		else
			(= useInsetRect 1)
			(= inLeft theInLeft)
			(= inTop theInTop)
			(= inRight theInRight)
			(= inBottom theInBottom)
		)
	)
	
	(method (ignoreActors param1)
		(if (or (== 0 argc) param1)
			(= signal (| signal $4000))
		else
			(= signal (& signal $bfff))
		)
	)
	
	(method (hide)
		(if (self isNotHidden:)
			(= signal (| signal $0008))
			(DeleteScreenItem self)
			(= -info- (& -info- $ffef))
		)
	)
	
	(method (lastLoop)
		(return (- (NumLoops self) 1))
	)
	
	(method (lastCel)
		(return (- (NumCels self) 1))
	)
	
	(method (motionCue)
	)
	
	(method (checkDetail)
	)
	
	(method (setScale param1 param2 &tmp temp0 temp1 temp2 temp3 temp4)
		(cond 
			((not argc)
				(= scaleSignal (| scaleSignal $0001))
				(= scaleSignal (& scaleSignal $fffd))
			)
			((== argc 2)
				(= temp3 (CelWide view loop cel))
				(= temp4 (CelHigh view loop cel))
				(if (== param1 -1) (= param1 temp3))
				(if (== param2 -1) (= param2 temp4))
				(= scaleX (/ (* param1 128) temp3))
				(= scaleY (/ (* param2 128) temp4))
				(= scaleSignal (| scaleSignal $0001))
				(UpdateScreenItem self)
			)
			((not param1) (= scaleSignal (& scaleSignal $fffc)))
			((< param1 (curRoom vanishingY?))
				(Printf
					{<%s setScale:> y value less than vanishingY}
					name
				)
			)
			(else
				(= temp0 (- param1 (curRoom vanishingY?)))
				(= temp2
					(+ (/ (* (= temp1 (- 190 param1)) 100) temp0) 100)
				)
				(= scaleSignal (| scaleSignal $0003))
				(= maxScale (/ (* temp2 128) 100))
			)
		)
	)
	
	(method (setMagnifier theMagnifier)
		(if (not (& signal $0008))
			(if magnifier (magnifier dispose:))
			(if (and argc theMagnifier)
				((= magnifier theMagnifier) init: self &rest)
			)
		)
	)
	
	(method (setScaleDirect theScaleX)
		(self setScale:)
		(if argc (= scaleY (= scaleX theScaleX)))
	)
	
	(method (setScalePercent param1)
		(self setScale:)
		(if argc
			(= scaleX (MulDiv param1 128 100))
			(= scaleY scaleX)
		)
	)
)

(class Prop of View
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
		signal $5021
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
		cycleSpeed 7
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
		code 0
	)
	
	(method (doit &tmp temp0)
		(if script (script doit:))
		(if code (code doit: self))
		(if (& signal $0001)
			(if cycler (cycler doit:))
			(if scaler (scaler doit:))
		)
		(super doit:)
	)
	
	(method (dispose)
		(self setScript: 0 setCycle: 0)
		(if timer (timer dispose:))
		(if scaler (scaler dispose:) (= scaler 0))
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if script (script handleEvent: event))
		(super handleEvent: event)
	)
	
	(method (setLoop &tmp theLoop)
		(= theLoop loop)
		(super setLoop: &rest)
		(if (and cycler (!= theLoop loop))
			(cycler clientLastCel: (self lastCel:))
		)
	)
	
	(method (motionCue)
		(if (and cycler (cycler completed?))
			(cycler motionCue:)
		)
	)
	
	(method (checkDetail param1)
		(cond 
			((not detailLevel))
			(
				(<
					(if argc param1 else (theGame detailLevel:))
					detailLevel
				)
				(= signal (& signal $fffe))
			)
			(else (= signal (| signal $0001)))
		)
	)
	
	(method (setScale param1)
		(if scaler (scaler dispose:) (= scaler 0))
		(if (not argc)
			(super setScale:)
		else
			(super setScale: param1)
		)
	)
	
	(method (setCycle cType)
		(if cycler (cycler dispose:))
		(if cType
			(= cycler
				(if (& (cType -info-?) $8000) (cType new:) else cType)
			)
			(cycler init: self &rest)
		else
			(= cycler 0)
		)
	)
	
	(method (setScript newScript)
		(if script (script dispose:))
		(if newScript (newScript init: self &rest))
	)
	
	(method (cue)
		(if script (script cue:))
	)
	
	(method (setScaler param1)
		(if scaler (scaler dispose:) (= scaler 0))
		(cond 
			((or (not argc) (not param1)) (return))
			((param1 isKindOf: Scaler)
				(= scaler
					(if (& (param1 -info-?) $8000)
						(param1 new:)
					else
						param1
					)
				)
				(= scaleSignal (| scaleSignal $0001))
				(= scaleSignal (& scaleSignal $fffd))
				(scaler init: self &rest)
			)
			(else
				(= scaler ((param1 scaler?) new:))
				(= scaleSignal (param1 scaleSignal?))
				(= maxScale (param1 maxScale?))
				(scaler client: self doit:)
			)
		)
	)
)

(class FaceCue of CueMe
	(properties
		scratch 0
		whoToTurn 0
		whatToFace 0
		whoToCue 0
	)
	
	(method (cue)
		(whoToTurn
			setHeading:
				(GetAngle
					(whoToTurn x?)
					(whoToTurn y?)
					(whatToFace x?)
					(whatToFace y?)
				)
				whoToCue
		)
		(whoToTurn facer: 0)
		(self dispose:)
	)
)

(class Actor of Prop
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
		signal $5021
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
		cycleSpeed 7
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
		code 0
		illegalBits $8000
		xLast 0
		yLast 0
		xStep 3
		origStep 770
		moveSpeed 7
		blocks 0
		baseSetter 0
		mover 0
		looper 0
		viewer 0
		avoider 0
		robot 0
		facer 0
	)
	
	(method (init)
		(super init: &rest)
		(= xLast x)
		(= yLast y)
	)
	
	(method (doit &tmp [temp0 2] temp2 temp3 temp4)
		(if robot (robot doit:))
		(if script (script doit:))
		(if code (code doit: self))
		(if (& signal $0001)
			(if viewer (viewer doit: self))
			(if avoider (avoider doit:))
			(if mover (mover doit:))
			(if cycler (cycler doit:))
			(if (& -info- $0008)
				(if scaler (scaler doit:))
				(= xLast x)
				(= yLast y)
				(if (self isNotHidden:) (UpdateScreenItem self))
				(if
					(and
						(& scaleSignal $0001)
						(not (& scaleSignal $0004))
						(!= scaleX oldScaleX)
					)
					(= oldScaleX scaleX)
					(= temp2
						(if
							(= temp4
								(>> (+ (* (>> origStep $0008) scaleX) 64) $0007)
							)
						else
							1
						)
					)
					(= temp3
						(if
							(= temp4
								(>> (+ (* (& origStep $00ff) scaleY) 64) $0007)
							)
						else
							1
						)
					)
					(if (or (!= temp2 xStep) (!= temp3 yStep))
						(self setStep: temp2 temp3 1)
					)
				)
				(cond 
					((not (& signal $0020)))
					(baseSetter (baseSetter doit: self))
					(else (BaseSetter self))
				)
			)
		)
	)
	
	(method (dispose)
		(if robot (self setRobot:))
		(if (!= mover -1) (self setMotion: 0))
		(self setAvoider: 0)
		(if baseSetter (baseSetter dispose:) (= baseSetter 0))
		(if looper (looper dispose:) (= looper 0))
		(if viewer (viewer dispose:) (= viewer 0))
		(if blocks (blocks dispose:) (= blocks 0))
		(if code (code dispose:) (= code 0))
		(super dispose:)
	)
	
	(method (posn theXLast theYLast)
		(super posn: theXLast theYLast &rest)
		(= xLast theXLast)
		(= yLast theYLast)
	)
	
	(method (motionCue)
		(if (and mover (mover completed?)) (mover motionCue:))
		(super motionCue:)
	)
	
	(method (checkDetail param1)
		(cond 
			((not detailLevel))
			(
				(<
					(if argc param1 else (theGame detailLevel:))
					detailLevel
				)
				(= signal (& signal $fffe))
			)
			(else (= signal (| signal $0001)))
		)
	)
	
	(method (setMotion mType &tmp temp0)
		(if (and mover (!= mover -1)) (mover dispose:))
		(if mType
			(= mover
				(if (& (mType -info-?) $8000) (mType new:) else mType)
			)
			(mover init: self &rest)
		else
			(= mover 0)
			(if
				(and
					looper
					(looper isMemberOf: GradualLooper)
					cycler
					(cycler isMemberOf: GradualCycler)
				)
				(looper caller: 0 oldMover: 0 cue:)
			)
		)
	)
	
	(method (setAvoider aType)
		(if avoider (avoider dispose:))
		(= avoider
			(if (and aType (& (aType -info-?) $8000))
				(aType new:)
			else
				aType
			)
		)
		(if avoider (avoider init: self &rest))
	)
	
	(method (ignoreHorizon param1)
		(if (or (not argc) param1)
			(= signal (| signal $2000))
		else
			(= signal (& signal $dfff))
		)
	)
	
	(method (observeControl ctrl &tmp temp0)
		(= temp0 0)
		(while (< temp0 argc)
			(= illegalBits (| illegalBits [ctrl temp0]))
			(++ temp0)
		)
	)
	
	(method (ignoreControl ctrl &tmp temp0)
		(= temp0 0)
		(while (< temp0 argc)
			(= illegalBits (& illegalBits (~ [ctrl temp0])))
			(++ temp0)
		)
	)
	
	(method (observeBlocks)
		(if (not blocks) (= blocks (Set new:)))
		(blocks add: &rest)
	)
	
	(method (ignoreBlocks)
		(if blocks
			(blocks delete: &rest)
			(if (blocks isEmpty:) (blocks dispose:) (= blocks 0))
		)
	)
	
	(method (isStopped)
		(return (if (not mover) else (& signal $0400)))
	)
	
	(method (isBlocked)
		(return (& signal $0400))
	)
	
	(method (inRect param1 param2 param3 param4)
		(return
			(if
			(and (<= param1 x) (<= x param3) (<= param2 y))
				(<= y param4)
			else
				0
			)
		)
	)
	
	(method (distanceTo anObj)
		(GetDistance x y (anObj x?) (anObj y?) perspective)
	)
	
	(method (cantBeHere &tmp temp0)
		(cond 
			((not (& signal $0020)))
			(baseSetter (baseSetter doit: self))
			(else (BaseSetter self))
		)
		(= temp0
			(cond 
				(
				(CantBeHere self ((plane getMainCast:) elements?)))
				(
					(and
						(not (& signal $2000))
						curRoom
						(< y (curRoom horizon?))
					)
					-1
				)
				(
				(and blocks (not (blocks allTrue: #doit self))) -2)
			)
		)
	)
	
	(method (setStep xs ys leaveOriginal &tmp theXStep theYStep)
		(= theXStep (>> origStep $0008))
		(= theYStep (& origStep $00ff))
		(if (and (>= argc 1) (!= xs -1)) (= theXStep xs))
		(if (and (>= argc 2) (!= ys -1)) (= theYStep ys))
		(if (or (< argc 3) (not leaveOriginal))
			(= origStep (+ (<< theXStep $0008) theYStep))
		)
		(= xStep theXStep)
		(= yStep theYStep)
		(if
			(and
				mover
				(or
					(mover isMemberOf: MoveTo)
					(mover isMemberOf: PolyPath)
				)
			)
			(mover init:)
		)
	)
	
	(method (setDirection dir &tmp temp0 curRoomVanishingY temp2 temp3 temp4 temp5 newIntArray curRoomObstacles temp8 temp9 temp10 temp11)
		(= temp0
			(if
			(== (= curRoomVanishingY (curRoom vanishingY?)) -30000)
				x
			else
				(curRoom vanishingX?)
			)
		)
		(if (and (== xStep 0) (== yStep 0)) (return))
		(= temp5 (/ 32000 (Max xStep yStep)))
		(switch dir
			(0 (self setMotion: 0) (return))
			(1
				(= temp2 (- temp0 x))
				(= temp3 (- curRoomVanishingY y))
			)
			(5
				(= temp2 (- x temp0))
				(= temp3 (- y curRoomVanishingY))
			)
			(3 (= temp2 temp5) (= temp3 0))
			(7
				(= temp2 (- temp5))
				(= temp3 0)
			)
			(else 
				(if
				(< 180 (= temp4 (GetAngle x y temp0 curRoomVanishingY)))
					(= temp4 (- temp4 360))
				)
				(= temp4 (+ (/ (+ temp4 90) 2) (* 45 (- dir 2))))
				(= temp2 (SinMult temp4 100))
				(= temp3 (- (CosMult temp4 100)))
			)
		)
		(= temp5 (/ temp5 5))
		(while
		(and (< (Abs temp3) temp5) (< (Abs temp2) temp5))
			(= temp2 (* temp2 5))
			(= temp3 (* temp3 5))
		)
		(if
			(and
				(= curRoomObstacles (curRoom obstacles?))
				useObstacles
			)
			(= newIntArray (IntArray new:))
			(= temp10 (+ (- (plane right:) (plane left:)) 1))
			(= temp11 (+ (- (plane bottom?) (plane top?)) 1))
			(newIntArray
				copy:
					(AvoidPath
						x
						y
						(+ x temp2)
						(+ y temp3)
						curRoomObstacles
						temp10
						temp11
						0
					)
			)
			(= temp8 (newIntArray at: 2))
			(= temp9 (newIntArray at: 3))
			(= temp2 (- temp8 x))
			(= temp3 (- temp9 y))
			(if altPolyList
				(newIntArray
					copy: (AvoidPath x y temp8 temp9 altPolyList temp10 temp11 0)
				)
				(if
					(or
						(!= (newIntArray at: 2) (+ x temp2))
						(!= (newIntArray at: 3) (+ y temp3))
					)
					(= temp2 (- (newIntArray at: 2) x))
					(= temp3 (- (newIntArray at: 3) y))
				)
			)
			(newIntArray dispose:)
		)
	)
	
	(method (setHeading h whoCares)
		(if argc (= heading h))
		(if looper
			(looper
				doit: self heading (if (>= argc 2) whoCares else 0)
			)
		else
			(DirLoop self heading)
			(if cycler (cycler clientLastCel: (self lastCel:)))
			(if (and (>= argc 2) whoCares) (whoCares cue: &rest))
		)
		(return heading)
	)
	
	(method (setSpeed theSpeed)
		(if argc (= moveSpeed (= cycleSpeed theSpeed)))
		(return moveSpeed)
	)
	
	(method (setLooper param1 &tmp theLooper)
		(if looper (looper dispose:))
		(if (and argc param1)
			(= theLooper
				(if (& (param1 -info-?) $8000)
					(param1 new:)
				else
					param1
				)
			)
			((= looper theLooper) init: self)
		)
	)
	
	(method (setRobot param1 param2 &tmp temp0)
		(cond 
			((and argc param1)
				(= robot
					(if (& (param1 -info-?) $8000)
						(param1 new:)
					else
						param1
					)
				)
				(= temp0 (if (and (> argc 1) param2) self else 0))
				(robot init: temp0 &rest)
			)
			(robot (robot dispose: argc) (= robot 0))
		)
	)
	
	(method (approach param1 param2)
		(if (< argc 2) (= param2 0))
		(if (== self param1)
			(if param2 (param2 cue:))
			(return)
		)
		(cond 
			(
				(>
					(GetDistance
						x
						y
						(param1 approachX?)
						(param1 approachY?)
					)
					(param1 approachDist?)
				)
				(self
					setMotion:
						PolyPath
						(param1 approachX?)
						(+ z (param1 approachY?))
						param2
				)
			)
			(param2 (param2 cue:))
		)
	)
	
	(method (approachThenFace param1 param2)
		(if (< argc 2) (= param2 0))
		(if (== self param1)
			(if param2 (param2 cue:))
			(return)
		)
		(if facer (facer dispose:))
		(= facer (FaceCue new:))
		(facer
			whoToTurn: self
			whatToFace: param1
			whoToCue: param2
		)
		(self approach: param1 facer)
	)
	
	(method (face param1 param2)
		(if (< argc 2) (= param2 0))
		(if (== self param1)
			(if param2 (param2 cue:))
			(return)
		)
		(self
			setHeading: (GetAngle x y (param1 x?) (param1 y?)) param2
		)
	)
	
	(method (stopwalk)
		(if (!= loop (self lastLoop:))
			(self setCel: loop setLoop: (self lastLoop:))
		)
	)
)
