;;; Sierra Script 1.0 - (do not remove this comment)
(script# 281)
(include sci.sh)
(use Main)
(use Kq6Procs)
(use Motion)
(use Actor)
(use System)

(public
	pawnShopGenie 0
	proc281_1 1
)

(procedure (proc281_1 param1)
	(if (cast contains: eye) (eye dispose:))
	(pawnShopGenie cycleSpeed: 6)
	(genieBrowseScr caller: param1 dispose:)
)

(instance pawnShopGenie of Actor
	(properties
		x 85
		y 131
		noun 5
		approachX 117
		approachY 133
		view 2831
		signal $4000
		cycleSpeed 75
	)
	
	(method (init param1)
		(super init: &rest)
		(self approachVerbs: 2 0)
		(if param1
			(self setScript: genieBrowseScr)
		else
			(curRoom setScript: (ScriptID 282 4))
		)
	)
	
	(method (dispose)
		(super dispose:)
		(self delete:)
		(DisposeScript 281)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13
				(curRoom setScript: (ScriptID 282 3))
			)
			(67
				(ego put: 31 -1)
				(curRoom setScript: (ScriptID 282 6))
			)
			(2
				(if (OneOf currentAct 4 5)
					(messager say: noun theVerb (if (Bset 122) 68 else 1))
				else
					(super doVerb: theVerb &rest)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance eye of Prop
	(properties
		x 92
		y 92
		view 902
		priority 10
		signal $0010
	)
)

(instance genieBrowseScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(pawnShopGenie view: 2831 loop: 0 cel: 0)
				(self cue:)
			)
			(1
				(pawnShopGenie
					cycleSpeed: (Random 30 75)
					setCycle: CT 3 1 self
				)
			)
			(2 (= ticks (Random 60 120)))
			(3
				(if
				(and (> (ego x?) 67) (OneOf (Random 0 2) 0 1))
					(eye init: cel: 0 cycleSpeed: 12 setCycle: End self)
				else
					(self cue:)
				)
			)
			(4 (= ticks 6))
			(5
				(eye dispose:)
				(= ticks (Random 60 120))
			)
			(6
				(pawnShopGenie setCycle: End self)
			)
			(7 (= state -1) (= ticks 25))
		)
	)
)
