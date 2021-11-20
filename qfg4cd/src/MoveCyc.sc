;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64942)
(include sci.sh)
(use Main)
(use Motion)


(class MCyc of Cycle
	(properties
		scratch 0
		client 0
		caller 0
		cycleDir 1
		cycleCnt 0
		completed 0
		clientLastCel 0
		value 0
		points 0
		size 0
	)
	
	(method (init theClient thePoints theCaller theCycleDir &tmp [temp0 2])
		(= client theClient)
		(= points thePoints)
		(if (> argc 2)
			(= caller theCaller)
			(if (> argc 3) (= cycleDir theCycleDir))
		)
		(= size 0)
		(while (!= (points at: size) -32768)
			(++ size)
		)
		(if (== cycleDir 1)
			(= value 0)
		else
			(= value (- size 4))
		)
		(super init:)
	)
	
	(method (doit)
		(if
		(>= (Abs (- gameTime cycleCnt)) (client cycleSpeed?))
			(= cycleCnt gameTime)
			(self nextCel:)
		)
	)
	
	(method (nextCel)
		(client
			loop: (points at: value)
			cel: (points at: (+ value 1))
			x: (points at: (+ value 2))
			y: (points at: (+ value 3))
		)
		(= value (+ value (* cycleDir 4)))
		(if
			(or
				(and (== cycleDir 1) (>= value size))
				(and (== cycleDir -1) (< value 0))
			)
			(self cycleDone:)
		)
	)
	
	(method (cycleDone)
		(= completed 1)
		(= value 0)
		(self motionCue:)
	)
)
