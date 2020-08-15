;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64907)
(include sci.sh)
(use Plane)
(use System)


(class ScrollableWindow of Object
	(properties
		scratch 0
		plane 0
		fore 255
		back 0
		font 0
		mode 0
		borderColor 255
		nsLeft 75
		nsTop 50
		nsRight 275
		nsBottom 150
		maxItems 20
		kWindow 0
	)
	
	(method (init theMaxItems)
		(if argc
			(if (== argc 1)
				(= maxItems theMaxItems)
			else
				(= nsLeft [theMaxItems 0])
				(= nsTop [theMaxItems 1])
				(= nsRight [theMaxItems 2])
				(= nsBottom [theMaxItems 3])
				(if (> argc 4) (= maxItems [theMaxItems 4]))
			)
		)
		(if (not plane) (= plane (Plane new:)))
		(= kWindow (ScrollWindow 0 self maxItems))
	)
	
	(method (dispose)
		(ScrollWindow 17 kWindow)
		(super dispose:)
	)
	
	(method (show)
		(ScrollWindow 16 kWindow)
	)
	
	(method (hide)
		(ScrollWindow 15 kWindow)
	)
	
	(method (addString param1)
		(ScrollWindow 1 kWindow param1 &rest)
	)
	
	(method (modifyString param1)
		(ScrollWindow 14 kWindow param1 &rest)
	)
	
	(method (insertString param1)
		(ScrollWindow 12 kWindow param1 &rest)
	)
	
	(method (erase)
		(ScrollWindow 2 kWindow)
	)
	
	(method (scrollTo param1 param2 param3)
		(switch param1
			(0 (ScrollWindow 7 kWindow))
			(1 (ScrollWindow 3 kWindow))
			(2 (ScrollWindow 5 kWindow))
			(3
				(ScrollWindow 11 kWindow param2 param3)
			)
			(4 (ScrollWindow 6 kWindow))
			(5 (ScrollWindow 4 kWindow))
			(6 (ScrollWindow 8 kWindow))
		)
	)
	
	(method (move theNsLeft theNsTop param3)
		(if (and (> argc 2) param3)
			(= nsLeft (+ nsLeft theNsLeft))
			(= nsRight (+ nsRight theNsLeft))
			(= nsTop (+ nsTop theNsTop))
			(= nsBottom (+ nsBottom theNsTop))
		else
			(= nsRight (+ nsRight (- theNsLeft nsLeft)))
			(= nsBottom (+ nsBottom (- theNsTop nsTop)))
			(= nsLeft theNsLeft)
			(= nsTop theNsTop)
		)
		(ScrollWindow 9 kWindow self)
	)
	
	(method (resize param1 param2 param3)
		(if (and (> argc 2) param3)
			(= nsRight (+ nsRight param1))
			(= nsBottom (+ nsBottom param2))
		else
			(= nsRight (+ nsLeft param1 -1))
			(= nsBottom (+ nsTop param2 -1))
		)
		(ScrollWindow 9 kWindow self)
	)
	
	(method (where param1)
		(ScrollWindow 10 kWindow param1)
	)
)
