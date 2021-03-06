;;; Sierra Script 1.0 - (do not remove this comment)
(script# GROOPER)
(include game.sh)
(use Main)
(use Sight)
(use Motion)
(use System)


(local
	[trans1 8] = [2 6 4 0 3 5 1 7]
	[trans2 8] = [loopN	loopNE loopE loopSE loopS loopSW	loopW	loopNW ]
)

(enum
	TRANS1
	TRANS2
)

(class GradualLooper of Code
	(properties
		name "Grooper"
		client 0
		oldCycler 0
		oldMover 0
		caller 0
	)
	
	(method (doit c h whoCares dontGroop &tmp lastDir numLoops)
		(if (not client) (= client c))
		(if (& (client signal?) fixedLoop)
			(if caller (caller cue:))
			(= caller 0)
			(return)
		)
		(if (>= argc 3) (= caller whoCares))
		(if
			(or
				(not (cast contains: client))
				(and (>= argc 4) dontGroop)
			)
			(= numLoops (NumLoops client))
			(client
				loop:
					(accessLocalArray
						doit:
							TRANS2
							(*
								(if (== numLoops 4) 2 else 1)
								(/
									(umod (+ (client heading?) (/ 180 numLoops)) 360)
									(/ 360 numLoops)
								)
							)
					)
			)
			(if caller (caller cue:))
			(= caller 0)
			(return)
		)
		(= lastDir (accessLocalArray doit: TRANS1 (client loop?)))
		(= numLoops (if (< (NumLoops client) 8) 4 else 8))
		(if oldMover
			(oldMover dispose:)
			(= oldMover 0)
		)
		(if
		(< (Abs (- (* 45 lastDir) h)) (+ (/ 180 numLoops) 1))
			(if caller (caller cue:))
			(= caller 0)
			(return)
		)
		(if
			(and
				(IsObject oldCycler)
				(or
					(oldCycler isMemberOf: GradualCycler)
					(not ((client cycler?) isMemberOf: GradualCycler))
				)
			)
			(oldCycler dispose:)
			(= oldCycler 0)
		)
		(if (not oldCycler) (= oldCycler (client cycler?)))
		(if
			(and
				(client cycler?)
				((client cycler?) isMemberOf: GradualCycler)
			)
			((client cycler?) dispose:)
		)
		(= oldMover (client mover?))
		(client
			cycler: 0
			mover: 0
			setMotion: 0
			setCycle: GradualCycler self lastDir
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
	
	(method (cue &tmp oldCaller)
		(if (not (IsObject (client mover?)))
			(client mover: oldMover)
		)
		(if (IsObject oldCycler) (client cycler: oldCycler))
		(= oldCaller caller)
		(= caller (= oldMover (= oldCycler 0)))
		(if oldCaller (oldCaller cue: &rest))
	)
)

(class GradualCycler of Cycle
	(properties
		name "Grycler"
		client 0
		caller 0
		cycleDir 1
		cycleCnt 0
		completed 0
		loopIndex 0
		numOfLoops 0
	)
	
	(method (init act whoCares oldDir)
		(super init: act)
		(= caller whoCares)
		(= numOfLoops (NumLoops client))
		(= cycleDir
			(-
				(sign
					(AngleDiff (* oldDir 45) (act heading?))
				)
			)
		)
		(= loopIndex oldDir)
	)
	
	(method (doit)
		(if
			(>=
				(Abs (AngleDiff (* loopIndex 45) (client heading?)))
				(+ (/ 180 numOfLoops) 1)
			)
			(client loop: (self nextCel:))
		else
			(self cycleDone:)
		)
	)
	
	(method (nextCel)
		(++ cycleCnt)
		(if (<= cycleCnt (client cycleSpeed?))
			(client loop?)
		else
			(= cycleCnt 0)
			(= loopIndex
				(+ loopIndex (* cycleDir (/ 8 numOfLoops)))
			)
			(= loopIndex (umod loopIndex 8))
			(accessLocalArray doit: TRANS2 loopIndex)
		)
	)
	
	(method (cycleDone)
		(= completed TRUE)
		(= doMotionCue TRUE)
	)
)

(instance accessLocalArray of Code
	(properties
		name "aLA"
	)
	
	(method (doit whichArray ind)
		(return (if (== whichArray 0) [trans1 ind] else [trans2 ind]))
	)
)
