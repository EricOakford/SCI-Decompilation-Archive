;;; Sierra Script 1.0 - (do not remove this comment)
(script# 977)
(include sci.sh)
(use Main)
(use Sight)
(use Motion)
(use System)


(local
	[trans1 8] = [2 6 4 0 3 5 1 7]
	[trans2 8] = [3 6 0 4 2 5 1 7]
)
(class Grooper of Code
	(properties
		client 0
		oldCycler 0
		oldMover 0
		caller 0
	)
	
	(method (doit theClient param2 theCaller param4 &tmp theTrans1 temp1)
		(if (not client) (= client theClient))
		(if (& (client signal?) $0800)
			(if caller (caller cue:))
			(= caller 0)
			(return)
		)
		(if (>= argc 3) (= caller theCaller))
		(= temp1 (if (< (NumLoops client) 8) 4 else 8))
		(if
			(or
				(not (cast contains: client))
				(and (>= argc 4) param4)
			)
			(client
				loop:
					[trans2 (*
						(if (== temp1 4) 2 else 1)
						(/
							(umod (+ (client heading?) (/ 180 temp1)) 360)
							(/ 360 temp1)
						)
					)]
			)
			(if caller (caller cue:))
			(= caller 0)
			(return)
		)
		(= theTrans1 [trans1 (client loop?)])
		(if oldMover (oldMover dispose:) (= oldMover 0))
		(if
			(and
				(IsObject oldCycler)
				(or
					(oldCycler isMemberOf: Grycler)
					(not ((client cycler?) isMemberOf: Grycler))
				)
			)
			(oldCycler dispose:)
			(= oldCycler 0)
		)
		(if (not oldCycler) (= oldCycler (client cycler?)))
		(if
			(and
				(client cycler?)
				((client cycler?) isMemberOf: Grycler)
			)
			((client cycler?) dispose:)
		)
		(= oldMover (client mover?))
		(client
			cycler: 0
			mover: 0
			setMotion: 0
			setCycle: Grycler self theTrans1
		)
	)
	
	(method (dispose)
		(if (IsObject oldCycler)
			(oldCycler dispose:)
			(= oldCycler 0)
		)
		(if (IsObject oldMover)
			(oldMover dispose:)
			(= oldMover 0)
		)
		(if client (client looper: 0))
		(super dispose:)
	)
	
	(method (cue &tmp theCaller)
		(if (not (IsObject (client mover?)))
			(client mover: oldMover)
		)
		(if (IsObject oldCycler) (client cycler: oldCycler))
		(= theCaller caller)
		(= caller (= oldMover (= oldCycler 0)))
		(if theCaller (theCaller cue: &rest))
	)
)

(class Grycler of Cycle
	(properties
		client 0
		caller 0
		cycleDir 1
		cycleCnt 0
		completed 0
		loopIndex 0
		numOfLoops 0
	)
	
	(method (init param1 theCaller theLoopIndex)
		(super init: param1)
		(= caller theCaller)
		(= numOfLoops (if (< (NumLoops client) 8) 4 else 8))
		(= cycleDir
			(-
				(sign
					(AngleDiff (* theLoopIndex 45) (param1 heading?))
				)
			)
		)
		(= loopIndex theLoopIndex)
		(if (self loopIsCorrect:) (self cycleDone:))
	)
	
	(method (doit)
		(client loop: (self nextCel:))
		(if (self loopIsCorrect:) (self cycleDone:))
	)
	
	(method (nextCel)
		(++ cycleCnt)
		(return
			(if
				(or
					(<= cycleCnt (client cycleSpeed?))
					(self loopIsCorrect:)
				)
				(client loop?)
			else
				(= cycleCnt 0)
				(= loopIndex
					(+ loopIndex (* cycleDir (/ 8 numOfLoops)))
				)
				(= loopIndex (umod loopIndex 8))
				[trans2 loopIndex]
			)
		)
	)
	
	(method (cycleDone)
		(= doMotionCue (= completed 1))
	)
	
	(method (loopIsCorrect)
		(return
			(<
				(Abs (AngleDiff (* loopIndex 45) (client heading?)))
				(+ (/ 180 numOfLoops) 1)
			)
		)
	)
)
