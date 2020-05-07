;;; Sierra Script 1.0 - (do not remove this comment)
(script# 998)
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
		signal $0000
		palette 0
	)
	
	(method (init)
		(addToPics add: self)
		(SetNowSeen self nsTop)
		(super init:)
	)
	
	(method (dispose)
		(DisposeClone self)
	)
	
	(method (showSelf)
		(Print name #icon view loop cel)
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
		signal $0101
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
	
	(method (init)
		(= signal (& signal $7fff))
		(if (not (cast contains: self))
			(= lsRight (= lsBottom (= lsLeft (= lsTop 0))))
			(= signal (& signal $ff77))
		)
		(BaseSetter self)
		(cast add: self)
		(if (not description) (= description name))
	)
	
	(method (dispose)
		(self startUpd: hide:)
		(= signal (| signal $8000))
	)
	
	(method (showSelf)
		(Print name #icon view loop cel)
	)
	
	(method (isNotHidden)
		(return (not (& signal $0080)))
	)
	
	(method (posn theX theY theZ)
		(if (>= argc 1)
			(= x theX)
			(if (>= argc 2)
				(= y theY)
				(if (>= argc 3) (= z theZ))
			)
		)
		(BaseSetter self)
		(self forceUpd:)
	)
	
	(method (stopUpd)
		(= signal (| signal stopUpdOn))
		(= signal (& signal $fffd))
	)
	
	(method (forceUpd)
		(= signal (| signal $0040))
	)
	
	(method (startUpd)
		(= signal (| signal $0002))
		(= signal (& signal $fffe))
	)
	
	(method (setPri thePriority)
		(cond 
			((== argc 0) (= signal (| signal fixPriOn)))
			((== thePriority -1) (= signal (& signal $ffef)))
			(else (= priority thePriority) (= signal (| signal fixPriOn)))
		)
		(self forceUpd:)
	)
	
	(method (setLoop theLoop)
		(cond 
			((== argc 0) (= signal (| signal noTurn)))
			((== theLoop -1) (= signal (& signal $f7ff)))
			(else (= loop theLoop) (= signal (| signal noTurn)))
		)
		(self forceUpd:)
	)
	
	(method (setCel param1)
		(cond 
			((== argc 0) (= signal (| signal skipCheck)))
			((== param1 -1) (= signal (& signal $efff)))
			(else
				(= signal (| signal skipCheck))
				(= cel
					(if (>= param1 (self lastCel:))
						(self lastCel:)
					else
						param1
					)
				)
			)
		)
		(self forceUpd:)
	)
	
	(method (ignoreActors param1)
		(if (or (== 0 argc) param1)
			(= signal (| signal ignAct))
		else
			(= signal (& signal $bfff))
		)
	)
	
	(method (hide)
		(= signal (| signal $0008))
	)
	
	(method (show)
		(= signal (& signal $fff7))
	)
	
	(method (delete)
		(if (& signal $8000)
			(if (& signal $0020)
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
			)
			(= signal (& signal $7fff))
			(cast delete: self)
			(if underBits (UnLoad 133 underBits) (= underBits 0))
			(DisposeClone self)
		)
	)
	
	(method (addToPic)
		(if (not (cast contains: self)) (self init:))
		(self signal: (| signal $8021))
	)
	
	(method (lastCel)
		(return (- (NumCels self) 1))
	)
	
	(method (isExtra param1 &tmp temp0)
		(= temp0 (& signal isExtra))
		(if argc
			(if param1
				(= signal (| signal isExtra))
			else
				(= signal (& signal $fdff))
			)
		)
		(return temp0)
	)
	
	(method (motionCue)
	)
)

(class Prop of View
	(properties
		signal $0000
		cycleSpeed 0
		script 0
		cycler 0
		timer 0
	)
	
	(method (doit &tmp temp0)
		(SetNowSeen self nsTop)
		(if (& signal $8000) (return))
		(if script (script doit:))
		(if (and (& signal $0004) (not (& signal $0002)))
			(return)
		)
		(if cycler (cycler doit:))
	)
	
	(method (handleEvent event)
		(if script (script handleEvent: event))
		(super handleEvent: event)
	)
	
	(method (delete)
		(if (& signal $8000)
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
	
	(method (setCycle theCycler)
		(if cycler (cycler dispose:))
		(if theCycler
			(self setCel: -1)
			(self startUpd:)
			(= cycler
				(if (& (theCycler -info-?) $8000)
					(theCycler new:)
				else
					theCycler
				)
			)
			(cycler init: self &rest)
		else
			(= cycler 0)
		)
	)
	
	(method (setScript theScript)
		(if (IsObject script) (script dispose:))
		(if theScript (theScript init: self &rest))
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
		(super init:)
		(= xLast x)
		(= yLast y)
	)
	
	(method (doit &tmp temp0 theBrLeft theBrRight)
		(if (& signal $8000) (return))
		(= signal (& signal $fbff))
		(if script (script doit:))
		(if code (code doit: self))
		(if (and (& signal $0004) (not (& signal $0002)))
			(return)
		)
		(if viewer (viewer doit: self))
		(cond 
			(avoider (avoider doit:))
			(mover (mover doit:))
		)
		(if cycler
			(= theBrLeft brLeft)
			(= theBrRight brRight)
			(cycler doit:)
			(if baseSetter
				(baseSetter doit: self)
			else
				(BaseSetter self)
			)
			(if
				(and
					(or (!= theBrLeft brLeft) (!= theBrRight brRight))
					(not (self canBeHere:))
				)
				(self findPosn:)
			)
		)
		(= xLast x)
		(= yLast y)
	)
	
	(method (posn theXLast theYLast)
		(super posn: theXLast theYLast &rest)
		(= xLast theXLast)
		(= yLast theYLast)
		(if (not (self canBeHere:)) (self findPosn:))
	)
	
	(method (setLoop param1 &tmp theLooper)
		(if
			(= theLooper
				(cond 
					((== argc 0) (super setLoop:) 0)
					((not (IsObject param1)) (super setLoop: param1 &rest) 0)
					((& (param1 -info-?) $8000) (param1 new:))
					(else param1)
				)
			)
			(if looper (looper dispose:))
			((= looper theLooper) init: self &rest)
		)
	)
	
	(method (delete)
		(if (& signal $8000)
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
	
	(method (setMotion theMover &tmp [temp0 40])
		(if (and mover (!= mover -1)) (mover dispose:))
		(if theMover
			(self startUpd:)
			(= mover
				(if (& (theMover -info-?) $8000)
					(theMover new:)
				else
					theMover
				)
			)
			(mover init: self &rest)
		else
			(= mover 0)
		)
	)
	
	(method (setAvoider theAvoider)
		(if avoider (avoider dispose:))
		(= avoider
			(if
				(and
					(IsObject theAvoider)
					(& (theAvoider -info-?) $8000)
				)
				(theAvoider new:)
			else
				theAvoider
			)
		)
		(if avoider (avoider init: self &rest))
	)
	
	(method (ignoreHorizon param1)
		(if (or (not argc) param1)
			(= signal (| signal ignoreHorizon))
		else
			(= signal (& signal $dfff))
		)
	)
	
	(method (observeControl bits &tmp temp0)
		(= temp0 0)
		(while (< temp0 argc)
			(= illegalBits (| illegalBits [bits temp0]))
			(++ temp0)
		)
	)
	
	(method (ignoreControl bits &tmp temp0)
		(= temp0 0)
		(while (< temp0 argc)
			(= illegalBits (& illegalBits (~ [bits temp0])))
			(++ temp0)
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
	
	(method (findPosn &tmp temp0 temp1 theX theY temp4)
		(= theX x)
		(= theY y)
		(= temp4 0)
		(= temp1 1)
		(while (not temp4)
			(= temp0 0)
			(while (and (not temp4) (< temp0 8))
				(= x
					(+ theX (* temp1 (sign (CosMult (* temp0 45) 100))))
				)
				(= y
					(- theY (* temp1 (sign (SinMult (* temp0 45) 100))))
				)
				(= temp4
					(if (self canBeHere:) (self onControl:) else 0)
				)
				(++ temp0)
			)
			(++ temp1)
		)
		(self posn: x y)
	)
	
	(method (inRect param1 param2 param3 param4)
		(return
			(if
			(and (<= param1 x) (< x param3) (<= param2 y))
				(< y param4)
			else
				0
			)
		)
	)
	
	(method (onControl fUsePoint)
		(if (and argc fUsePoint)
			(OnControl 4 x y)
		else
			(OnControl 4 brLeft brTop brRight brBottom)
		)
	)
	
	(method (distanceTo pObj)
		(GetDistance x y (pObj x?) (pObj y?) perspective)
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
					(and
						(== illegalBits 0)
						(& signal ignrAct)
					)
					(CantBeHere self (cast elements?))
				)
				(or
					(& signal ignoreHorizon)
					(not (IsObject curRoom))
					(>= y (curRoom horizon?))
				)
			)
			(if (== blocks 0) else (blocks allTrue: #doit self))
		)
	)
	
	(method (setStep newX newY)
		(if (and (>= argc 1) (!= newX -1)) (= xStep newX))
		(if (and (>= argc 2) (!= newY -1)) (= yStep newY))
		(if
		(and mover (!= -1 mover) (mover isMemberOf: MoveTo))
			((self mover?) init:)
		)
	)
	
	(method (setDirection newDirection &tmp temp0 curRoomVanishingY temp2 temp3 temp4 temp5)
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
		(switch newDirection
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
				(= temp4
					(+ (/ (+ temp4 90) 2) (* 45 (- newDirection 2)))
				)
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
		(self setMotion: MoveTo (+ x temp2) (+ y temp3))
	)
	
	(method (setHeading theHeading param2)
		(if argc (= heading theHeading))
		(if looper
			(looper
				doit: self heading (if (>= argc 2) param2 else 0)
			)
		else
			(DirLoop self heading)
			(Animate (cast elements?) 0)
			(if (and (>= argc 2) (IsObject param2)) (param2 cue:))
		)
		(return heading)
	)
)
