;;; Sierra Script 1.0 - (do not remove this comment)
(script# 80)
(include game.sh)
(use Array)
(use System)

(public
	HotSpot 0
)

(class HotSpot of Object
	(properties
		scratch 0
		hotVerbs 0
	)
	
	(method (dispose)
		(if hotVerbs
			(hotVerbs dispose:)
			(= hotVerbs 0)
		)
		(super dispose:)
	)
	
	(method (respondsTo theVerb &tmp i)
		(for ((= i 0)) (< i (hotVerbs size:)) ((++ i))
			(if (== (hotVerbs at: i) theVerb)
				(return TRUE)
			)
		)
		(return FALSE)
	)
	
	(method (respondVerbs theVerb &tmp i)
		(if hotVerbs (hotVerbs dispose:) (= hotVerbs 0))
		(if argc
			(= hotVerbs (ByteArray new:))
			(for ((= i 0)) (< i argc) ((++ i))
				(hotVerbs at: i [theVerb i])
			)
		)
	)
)
