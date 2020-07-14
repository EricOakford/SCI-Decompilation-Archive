;;; Sierra Script 1.0 - (do not remove this comment)
(script# GROOPER)
(include game.sh)
(use Main)
(use Sight)
(use Motion)
(use System)


(local
	[trans1 8] = [2 6 4 0 3 5 1 7]
	[trans2 8] = [loopN loopNE loopE loopSE loopS loopSW loopW loopNW]
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
		(= numLoops (if (< (NumLoops client) 8) 4 else 8))
		(if
			(or
				(not (cast contains: client))
				(and (>= argc 4) dontGroop)
			)
			(client
				loop:
					[trans2 (*
						(if (== numLoops 4) 2 else 1)
						(/
							(umod (+ (client heading?) (/ 180 numLoops)) 360)
							(/ 360 numLoops)
						)
					)]
			)
			(if caller (caller cue:))
			(= caller 0)
			(return)
		)
		(= lastDir [trans1 (client loop?)])
		(if oldMover (oldMover dispose:) (= oldMover 0))
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
			(= oldCycler NULL)
		)
		(if (IsObject oldMover)
			(oldMover dispose:)
			(= oldMover NULL)
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
		loopIndex 0
		numOfLoops 0
	)
	
	(method (init act whoCares oldDir)
		(super init: act)
		(= caller whoCares)
		(= numOfLoops (if (< (NumLoops client) 8) 4 else 8))
		(= cycleDir
			(-
				(sign
					(AngleDiff (* oldDir 45) (act heading?))
				)
			)
		)
		(= loopIndex oldDir)
		(if (self loopIsCorrect:) (self cycleDone:))
	)
	
	(method (doit)
		(client loop: (self nextCel:))
		(if (self loopIsCorrect:) (self cycleDone:))
	)
	
	(method (nextCel)
		(return
			(if
				(or
					(< (Abs (- gameTime cycleCnt)) (client cycleSpeed?))
					(self loopIsCorrect:)
				)
				(client loop?)
			else
				(= cycleCnt gameTime)
				(= loopIndex
					(+ loopIndex (* cycleDir (/ 8 numOfLoops)))
				)
				(= loopIndex (umod loopIndex 8))
				[trans2 loopIndex]
			)
		)
	)
	
	(method (cycleDone)
		(= doMotionCue (= completed TRUE))
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
