;;; Sierra Script 1.0 - (do not remove this comment)
(script# PUNCHCARD)
(include game.sh)
(use Main)
(use Inset)
(use Actor)
(use System)

(public
	punchCardInit 0
)

(local
	local0
	local1
	local2 =  10
)
(procedure (localproc_0036 &tmp temp0 temp1)
	(if
	(> 0 (= temp0 (- mouseX (+ 5 (punchCard x?)))))
		(return -1)
	)
	(if (> (/ temp0 20) 2) (return -1))
	(= temp1 (/ temp0 20))
	(if
	(> 0 (= temp0 (- mouseY (+ 5 (punchCard y?)))))
		(return -1)
	)
	(if (> (/ temp0 15) 2) (return -1))
	(return (+ (* (= temp0 (/ temp0 15)) 3) temp1))
)

(procedure (localproc_0097 param1)
	(= local1 (mod param1 3))
	(return (+ 5 (punchCard x?) (* (mod param1 3) 20)))
)

(procedure (localproc_00b4 param1)
	(= local0 (/ param1 3))
	(return (+ 5 (punchCard y?) (* (/ param1 3) 15)))
)

(procedure (localproc_00ff &tmp i)
	(= i 0)
	(while (< i 9)
		(if (& global129 (<< $0001 i))
			(DrawCel 616 1 1
				(localproc_0097 i)
				(localproc_00b4 i)
				15
			)
		)
		(++ i)
	)
	(Graph
		GShowBits
		(+ 5 (punchCard y?))
		(+ 5 (punchCard x?))
		(+ 5 (punchCard y?) 45)
		(+ 5 (punchCard x?) 60)
		1
	)
)

(procedure (localproc_0171 param1)
	(= global129 (| global129 (<< $0001 param1)))
	(localproc_00ff)
)

(instance punchCardInit of Code
	(properties)
	
	(method (doit)
		(if (not (curRoom inset:))
			(curRoom setInset: punchCard hl)
		)
	)
)

(instance punchCard of Inset
	(properties
		view 616
		x 110
		y 55
		priority 13
	)
	
	(method (init)
		(user canControl: FALSE canInput: FALSE)
		(super init: &rest)
		(SolvePuzzle fPunchedHolesInCard 5)
		(hl init:)
		(theExit init:)
	)
	
	(method (doit)
		(if (GameIsRestarting)
			(localproc_00ff)
			(if oldCast
				(oldCast eachElementDo: #forceUpd)
			)
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(user canControl: TRUE canInput: TRUE)
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(V_HOLE_PUNCH
				(if (!= (= temp0 (localproc_0036)) -1)
					(localproc_0171 temp0)
				)
			)
		)
	)
)

(instance hl of Prop
	(properties
		x 110
		y 55
		view 616
		loop 2
		priority 14
		signal (| ignrAct fixPriOn)
	)
	
	(method (init)
		(super init: &rest)
	)
	
	(method (doit &tmp temp0)
		(if (< local2 1)
			(if (not local2)
				(= local2 -1)
				(localproc_00ff)
				(user canControl: 1 canInput: 1)
				(theGame setCursor: ((theIconBar curIcon?) cursor?))
			)
		else
			(-- local2)
		)
		(if (!= (= temp0 (localproc_0036)) -1)
			(self
				x: (localproc_0097 temp0)
				y: (localproc_00b4 temp0)
			)
			(if (& global129 (<< $0001 temp0))
				(self cel: 1)
			else
				(self cel: 0)
			)
		)
		(super doit: &rest)
	)
	
	(method (cue)
		(UnLoad RES_VIEW 616)
		(DisposeScript PUNCHCARD)
	)
)

(instance theExit of View
	(properties
		x 146
		y 108
		view 616
		loop 3
		priority 14
		signal $4011
	)
	
	(method (doVerb &tmp temp0)
		(punchCard dispose:)
	)
)
