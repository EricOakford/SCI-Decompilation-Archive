;;; Sierra Script 1.0 - (do not remove this comment)
(script# 631)
(include sci.sh)
(use Main)
(use GloryRm)
(use Scaler)
(use Feature)
(use Motion)
(use Actor)

(public
	rm631 0
)

(instance rm631 of GloryRm
	(properties
		modNum 640
		picture 640
		east 644
	)
	
	(method (init)
		(switch prevRoomNum
			(644 (ego posn: 297 152 hide:))
			(662 (ego posn: 9 116))
			(else  (ego posn: 297 152))
		)
		(ego
			init:
			setScaler: Scaler 100 100 189 0
			normalize: (if (== prevRoomNum 644) 1 else 0)
		)
		(super init: &rest)
		(vLtSconces
			ignoreActors:
			approachVerbs: 4
			init:
			setCycle: Fwd
		)
		(vRtSconces
			ignoreActors:
			approachVerbs: 4
			init:
			setCycle: Fwd
		)
		(pFire ignoreActors: approachVerbs: 4 init: setCycle: Fwd)
		(vLtColumn ignoreActors: init:)
		(vRtColumn ignoreActors: init:)
		(vRug ignoreActors: init:)
		(vTable
			ignoreActors:
			init:
			signal: (| (vTable signal?) $1000)
		)
		(vFirePlace ignoreActors: approachVerbs: 4 init:)
		(vPainting ignoreActors: approachVerbs: 4 init:)
		(fPot init: approachVerbs: 4)
		(fMantel init: approachVerbs: 4)
		((ScriptID 634 0) init:)
	)
	
	(method (dispose)
		(if script (script dispose:))
		(DisposeScript 634)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(messager say: 0 1 0 0 0 631)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance vLtSconces of Prop
	(properties
		noun 21
		modNum 640
		x 51
		y 58
		priority 97
		fixPriority 1
		view 699
		loop 7
		cel 4
		detailLevel 2
	)
)

(instance vRtSconces of Prop
	(properties
		noun 21
		modNum 640
		x 244
		y 63
		view 699
		loop 7
		detailLevel 2
	)
)

(instance pFire of Prop
	(properties
		noun 31
		modNum 640
		x 157
		y 113
		priority 110
		fixPriority 1
		view 679
		loop 2
		cel 4
		detailLevel 2
	)
)

(instance vLtColumn of View
	(properties
		modNum 640
		x 43
		y -2
		priority 45
		fixPriority 1
		view 685
	)
)

(instance vRtColumn of View
	(properties
		modNum 640
		x 228
		y -1
		view 685
		loop 2
	)
)

(instance vRug of View
	(properties
		noun 33
		modNum 640
		approachX 168
		approachY 169
		x 8
		y 123
		view 692
	)
)

(instance vTable of View
	(properties
		noun 28
		modNum 640
		approachX 168
		approachY 169
		x 87
		y 125
		z 31
		priority 163
		fixPriority 1
		view 697
	)
)

(instance vFirePlace of View
	(properties
		noun 31
		modNum 640
		x 73
		y 44
		priority 60
		fixPriority 1
		view 679
		signal $1001
	)
)

(instance vPainting of View
	(properties
		noun 5
		modNum 640
		x 127
		y 8
		z 20
		view 698
		loop 6
	)
)

(instance fMantel of Feature
	(properties
		noun 32
		modNum 640
		nsLeft 77
		nsTop 40
		nsRight 225
		nsBottom 64
		sightAngle 180
		x 151
		y 52
	)
)

(instance fPot of Feature
	(properties
		noun 30
		modNum 640
		nsLeft 81
		nsTop 89
		nsRight 105
		nsBottom 112
		sightAngle 180
		x 93
		y 100
	)
)
