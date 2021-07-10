;;; Sierra Script 1.0 - (do not remove this comment)
(script# 387)
(include game.sh)
(use Class_399_3)
(use LoadMany)
(use Actor)

(public
	proc387_0 0
)

(procedure (proc387_0)
	(LoadMany SCRIPT 343 325 324 323 322 321)
	(Class_399_3
		at:
			20
			((View new:)
				init:
				view: 627
				loop: 2
				cel: 10
				x: 195
				y: 41
				setPri: 14
				stopUpd:
				yourself:
			)
	)
	((ScriptID 399 1) init:)
	((ScriptID 399 2) init:)
)
