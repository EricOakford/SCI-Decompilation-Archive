;;; Sierra Script 1.0 - (do not remove this comment)
(script# 2600)
(include game.sh)
(use Main)
(use Procs)
(use KQRoom)
(use Print)
(use Feature)
(use Actor)

(public
	rm2600 0
)

(local
	local0
)
(instance rm2600 of KQRoom
	(properties
		picture 2600
		west 2550
	)
	
	(method (init)
		(ego x: 150)
		(super init:)
		(self setRect: 0 0 640 136)
		(ego x: 150 y: 100 view: 900 init: loop: 0 cel: 0)
		(dragon init:)
		(gemPile init:)
	)
)

(instance dragon of View
	(properties
		noun 2
		x 150
		y 100
		view 2600
	)
	
	(method (init)
		(super init: &rest)
		(cond 
			((Btst 63))
			((Btst 60) (nose init:) (tail init:))
			(else (self setHotspot: 8 10 44 43 41 38 97 46))
		)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(44
					(messager say: noun theVerb 0 0)
					(Bset 60)
					(ego get: 37)
					(ego put: 36)
					(self dispose:)
					(return 1)
				)
				(8
					(cond 
						((Btst 326) (messager say: noun theVerb 3 0))
						((Btst 325) (Bset 326) (messager say: noun theVerb 2 0))
						(else (Bset 325) (messager say: noun theVerb 1 0))
					)
					(return 1)
				)
				(else  (messager say: 0 0 4 0))
			)
		)
	)
)

(instance tail of Feature
	(properties
		noun 4
		nsLeft 178
		nsTop 95
		nsRight 220
		nsBottom 138
		y 101
	)
	
	(method (init)
		(super init: &rest)
		(self setHotspot: 8 10 34 46)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(46
				(Prints
					{The dragon half wakes up, but Rose gets a scale.}
				)
				(ego get: iDragScale)
				(Bset 63)
				(nose dispose:)
				(self dispose:)
			)
			(34
				(messager say: noun theVerb 0 0)
			)
			(8
				(messager say: noun theVerb 0 0)
			)
		)
		(return 1)
	)
)

(instance nose of Feature
	(properties
		noun 3
		nsLeft 147
		nsTop 95
		nsRight 178
		nsBottom 138
		y 101
	)
	
	(method (init)
		(super init: &rest)
		(self setHotspot: 8 10 46)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(46
				(messager say: noun theVerb 1 1)
				(self dispose:)
			)
			(8
				(Prints {nose})
				(if local0
					(messager say: noun theVerb 2 1)
				else
					(= local0 1)
					(messager say: noun theVerb 1 1)
				)
			)
		)
	)
)

(instance gemPile of Feature
	(properties
		noun 1
	)
	
	(method (init)
		(super init: &rest)
		(self setHotspot: 8 10)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(8
					(messager say: noun theVerb 1)
					(self dispose:)
					(return 1)
				)
			)
		)
	)
)
