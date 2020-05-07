;;; Sierra Script 1.0 - (do not remove this comment)
(script# ACTOR)
(include game.sh)
(use Main)
(use Intrface)
(use Feature)
(use Motion)
(use System)


(class PicView of Feature
	(properties
		view 0
		loop 0
		cel 0
		priority -1
		signal 0
		palette 0
	)
	
	(method (init)
		(addToPics add: self)
		(SetNowSeen self)
		(super init: &rest)
	)
	
	(method (dispose)
		(features delete: self)
		(DisposeClone self)
	)
	
	(method (showSelf)
		(Print name #icon view loop cel)
	)
	
	(method (onMe)
		(if (& signal actorHidden) FALSE
		else (super onMe: &rest)
		)
	)
)

(class View of Feature
	(properties
		yStep 2
		view 0
		loop 0
		cel 0
		priority 0
		underBits 0
		signal (| stopUpdOn staticView)
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		palette 0
	)
	
	(method (init theInitializer)
		(= signal (& signal (~ delObj)))
		(if (not (cast contains: self))
			(= lsRight (= lsBottom (= lsLeft (= lsTop 0))))
			(= signal (& signal (~ (| hideActor actorHidden))))
		)
		(BaseSetter self)
		(super init: &rest)
	)
	
	(method (dispose)
		(self startUpd: hide:)
		(= signal (| signal delObj))
	)
	
	(method (showSelf)
		(Print name #icon view loop cel)
	)
	
	(method (isNotHidden)
		(return (not (& signal hideActor)))
	)
	
	(method (onMe)
		(if (& signal actorHidden) FALSE
		else (super onMe: &rest)
		)
	)
	
	(method (posn newX newY newZ)
		(if (>= argc 1)
			(= x newX)
			(if (>= argc 2)
				(= y newY)
				(if (>= argc 3) (= z newZ))
			)
		)
		(BaseSetter self)
		(self forceUpd:)
	)
	
	(method (stopUpd)
		(= signal (| signal stopUpdOn))
		(= signal (& signal (~ startUpdOn)))
	)
	
	(method (forceUpd)
		(= signal (| signal forceUpdOn))
	)
	
	(method (startUpd)
		(= signal (| signal startUpdOn))
		(= signal (& signal (~ stopUpdOn)))
	)
	
	(method (setPri newPri)
		(cond 
			((== argc 0)
				(= signal (| signal fixPriOn))
			)
			((== newPri -1)
				(= signal (& signal (~ fixPriOn)))
			)
			(else
				(= priority newPri) (= signal (| signal fixPriOn))
			)
		)
		(self forceUpd:)
	)
	
	(method (setLoop newLoop)
		(cond 
			((== argc 0)
				(= signal (| signal fixedLoop))
			)
			((== newLoop -1)
				(= signal (& signal (~ fixedLoop)))
			)
			(else
				(= loop newLoop)
				(= signal (| signal fixedLoop))
			)
		)
		(self forceUpd:)
	)
	
	(method (setCel newCel)
		(cond 
			((== argc 0)
				(= signal (| signal fixedCel))
			)
			((== newCel -1)
				(= signal (& signal (~ fixedCel)))
			)
			(else
				(= signal (| signal fixedCel))
				(= cel
					(if (>= newCel (self lastCel:))
						(self lastCel:)
					else
						newCel
					)
				)
			)
		)
		(self forceUpd:)
	)
	
	(method (ignoreActors arg)
		(if (or (== 0 argc) arg)
			(= signal (| signal ignrAct))
		else
			(= signal (& signal (~ ignrAct)))
		)
	)
	
	(method (hide)
		(= signal (| signal hideActor))
	)
	
	(method (show)
		(= signal (& signal (~ hideActor)))
	)
	
	(method (delete)
		(if (& signal delObj)
			(= signal (& signal (~ delObj)))
			(cast delete: self)
			(if underBits (UnLoad MEMORY underBits) (= underBits 0))
			(if (& signal viewAdded)
				(addToPics
					add:
						((PicView new:)
							view: view
							loop: loop
							cel: cel
							x: x
							y: y
							z: z
							priority: priority
							signal: signal
							yourself:
						)
				)
				(features add: self)
			else
				(DisposeClone self)
			)
		)
	)
	
	(method (addToPic)
		(if (not (cast contains: self)) (self init:))
		(self signal: (| signal ADDTOPIC))
	)
	
	(method (lastCel)
		(return (- (NumCels self) 1))
	)
	
	(method (isExtra value &tmp ret)
		(= ret (& signal anExtra))
		(if argc
			(if value
				(= signal (| signal anExtra))
			else
				(= signal (& signal (~ anExtra)))
			)
		)
		(return ret)
	)
	
	(method (motionCue)
	)
)

(class Prop of View
	(properties
		signal 0
		cycleSpeed 0
		script 0
		cycler 0
		timer 0
	)
	
	(method (doit &tmp aState)
		(SetNowSeen self nsTop)
		(if (& signal delObj) (return))
		(if script (script doit:))
		(if (and (& signal notUpd) (not (& signal startUpdOn)))
			(return)
		)
		(if cycler (cycler doit:))
	)
	
	(method (handleEvent event)
		(if script (script handleEvent: event))
		(super handleEvent: event)
	)
	
	(method (delete)
		(if (& signal delObj)
			(self setScript: 0 setCycle: 0)
			(if timer (timer dispose:))
			(super delete:)
		)
	)
	
	(method (motionCue)
		(if (and cycler (cycler completed?))
			(cycler motionCue:)
		)
	)
	
	(method (setCycle cType)
		(if cycler (cycler dispose:))
		(if cType
			(self setCel: -1)
			(self startUpd:)
			(= cycler
				(if (& (cType -info-?) CLASS) (cType new:) else cType)
			)
			(cycler init: self &rest)
		else
			(= cycler 0)
		)
	)
	
	(method (setScript newScript)
		(if (IsObject script) (script dispose:))
		(if newScript (newScript init: self &rest))
	)
	
	(method (cue)
		(if script (script cue:))
	)
)

(class Actor of Prop
	(properties
		illegalBits $8000
		xLast 0
		yLast 0
		xStep 3
		moveSpeed 0
		blocks 0
		baseSetter 0
		mover 0
		looper 0
		viewer 0
		avoider 0
		code 0
	)
	
	(method (init)
		(super init: &rest)
		(= xLast x)
		(= yLast y)
	)
	
	(method (doit &tmp aState left right)
		(if (& signal delObj) (return))
		(if script (script doit:))
		(if code (code doit: self))
		(if (and (& signal notUpd) (not (& signal startUpdOn)))
			(return)
		)
		(if viewer (viewer doit: self))
		(cond 
			(avoider (avoider doit:))
			(mover (mover doit:))
		)
		(if cycler
			(= left brLeft)
			(= right brRight)
			(cycler doit:)
			(if baseSetter
				(baseSetter doit: self)
			else
				(BaseSetter self)
			)
			(if
				(and
					(or (!= left brLeft) (!= right brRight))
					(not (self canBeHere:))
				)
				(self findPosn:)
			)
		)
		(= xLast x)
		(= yLast y)
	)
	
	(method (posn newX newY)
		(super posn: newX newY &rest)
		(= xLast newX)
		(= yLast newY)
		(if (not (self canBeHere:)) (self findPosn:))
	)
	
	(method (setLoop l &tmp newLooper)
		(if
			(= newLooper
				(cond 
					((== argc 0) (super setLoop:) 0)
					((not (IsObject l)) (super setLoop: l &rest) 0)
					((& (l -info-?) CLASS) (l new:))
					(else l)
				)
			)
			(if looper (looper dispose:))
			((= looper newLooper) init: self &rest)
		)
	)
	
	(method (delete)
		(if (& signal delObj)
			(if (!= mover -1) (self setMotion: 0))
			(self setAvoider: 0)
			(if baseSetter (baseSetter dispose:) (= baseSetter 0))
			(if looper (looper dispose:) (= looper 0))
			(if viewer (viewer dispose:) (= viewer 0))
			(if blocks (blocks dispose:) (= blocks 0))
			(if code (code dispose:) (= code 0))
			(if actions (actions dispose:) (= actions 0))
			(super delete:)
		)
	)
	
	(method (motionCue)
		(if (and mover (mover completed?)) (mover motionCue:))
		(super motionCue:)
	)
	
	(method (setMotion mType &tmp [str 40])
		(if (and mover (!= mover -1)) (mover dispose:))
		(if mType
			(self startUpd:)
			(= mover
				(if (& (mType -info-?) CLASS) (mType new:) else mType)
			)
			(mover init: self &rest)
		else
			(= mover 0)
		)
	)
	
	(method (setAvoider aType)
		(if avoider (avoider dispose:))
		(= avoider
			(if
			(and (IsObject aType) (& (aType -info-?) CLASS))
				(aType new:)
			else
				aType
			)
		)
		(if avoider (avoider init: self &rest))
	)
	
	(method (ignoreHorizon arg)
		(if (or (not argc) arg)
			(= signal (| signal ignrHrz))
		else
			(= signal (& signal (~ ignrHrz)))
		)
	)
	
	(method (observeControl ctrl &tmp i)
		(= i 0)
		(while (< i argc)
			(= illegalBits (| illegalBits [ctrl i]))
			(++ i)
		)
	)
	
	(method (ignoreControl ctrl &tmp i)
		(= i 0)
		(while (< i argc)
			(= illegalBits (& illegalBits (~ [ctrl i])))
			(++ i)
		)
	)
	
	(method (observeBlocks)
		(if (not blocks) (= blocks (Set new:)))
		(blocks add: &rest)
	)
	
	(method (ignoreBlocks)
		(blocks delete: &rest)
		(if (blocks isEmpty:) (blocks dispose:) (= blocks 0))
	)
	
	(method (isStopped)
		(return
			(cond 
				((not (IsObject mover)))
				((== x (mover xLast?)) (== y (mover yLast?)))
			)
		)
	)
	
	(method (isBlocked)
		(return (& signal $0400))
	)
	
	(method (findPosn &tmp legDir legLen xOrg yOrg goodPosn)
		(= xOrg x)
		(= yOrg y)
		(= goodPosn FALSE)
		(= legLen 1)
		(while (not goodPosn)
			(= legDir 0)
			(while (and (not goodPosn) (< legDir 8))
				(= x
					(+ xOrg (* legLen (sign (CosMult (* legDir 45) 100))))
				)
				(= y
					(- yOrg (* legLen (sign (SinMult (* legDir 45) 100))))
				)
				(= goodPosn
					(if (self canBeHere:) (self onControl:) else 0)
				)
				(++ legDir)
			)
			(++ legLen)
		)
		(self posn: x y)
	)
	
	(method (inRect lx uy rx by)
		(return
			(if
			(and (<= lx x) (< x rx) (<= uy y))
				(< y by)
			else
				0
			)
		)
	)
	
	(method (onControl org)
		(if (and argc org)
			(OnControl CMAP x y)
		else
			(OnControl CMAP brLeft brTop brRight brBottom)
		)
	)
	
	(method (distanceTo anObj)
		(GetDistance x y (anObj x?) (anObj y?) perspective)
	)
	
	(method (canBeHere)
		(if baseSetter
			(baseSetter doit: self)
		else
			(BaseSetter self)
		)
		(if
			(and
				(or
					(and (== illegalBits 0) (& signal ignrAct))
					(CanBeHere self (cast elements?))
				)
				(or
					(& signal ignrHrz)
					(not (IsObject curRoom))
					(>= y (curRoom horizon?))
				)
			)
			(if (== blocks 0) else (blocks allTrue: #doit self))
		)
	)
	
	(method (setStep xs ys)
		(if (and (>= argc 1) (!= xs -1)) (= xStep xs))
		(if (and (>= argc 2) (!= ys -1)) (= yStep ys))
		(if
		(and mover (!= -1 mover) (mover isMemberOf: MoveTo))
			((self mover?) init:)
		)
	)
	
	(method (setDirection dir &tmp vx vy xIncr yIncr ang maxCoord pathPts obs)
		(= vx
			(if
			(== (= vy (curRoom vanishingY?)) -30000)
				x
			else
				(curRoom vanishingX?)
			)
		)
		(if (and (== xStep 0) (== yStep 0)) (return))
		(= maxCoord (/ 32000 (Max xStep yStep)))
		(switch dir
			(dirStop
				(self setMotion: 0)
				(return)
			)
			(dirN
				(= xIncr (- vx x))
				(= yIncr (- vy y))
			)
			(dirS
				(= xIncr (- x vx))
				(= yIncr (- y vy))
			)
			(dirE
				(= xIncr maxCoord)
				(= yIncr 0)
			)
			(dirW
				(= xIncr (- maxCoord))
				(= yIncr 0)
			)
			(else 
				(if
				(< 180 (= ang (GetAngle x y vx vy)))
					(= ang (- ang 360))
				)
				(= ang (+ (/ (+ ang 90) 2) (* 45 (- dir 2))))
				(= xIncr (SinMult ang 100))
				(= yIncr (- (CosMult ang 100)))
			)
		)
		(= maxCoord (/ maxCoord 5))
		(while
		(and (< (Abs yIncr) maxCoord) (< (Abs xIncr) maxCoord))
			(= xIncr (* xIncr 5))
			(= yIncr (* yIncr 5))
		)
		(if
			(and
				(= obs (curRoom obstacles?))
				useObstacles
			)
			(= pathPts
				(AvoidPath
					x
					y
					(+ x xIncr)
					(+ y yIncr)
					(obs elements?)
					(obs size?)
					0
				)
			)
			(= xIncr (- (WordAt pathPts 2) x))
			(= yIncr (- (WordAt pathPts 3) y))
			(Memory MDisposePtr pathPts)
		)
		(cond 
			((or xIncr yIncr) (self setMotion: MoveTo (+ x xIncr) (+ y yIncr)))
			(dir (self setMotion: 0 setHeading: (* (- dir 1) 45)))
			(else (self setMotion: 0))
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
			(Animate (cast elements?) FALSE)
			(if (and (>= argc 2) (IsObject whoCares))
				(whoCares cue:)
			)
		)
		(return heading)
	)
)
