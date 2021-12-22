;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64907)
(include game.sh)
(use Plane)
(use System)


(class ScrollableWindow of Object
	(properties
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
		(= kWindow (ScrollWindow ScrollCreate self maxItems))
	)
	
	(method (dispose)
		(ScrollWindow ScrollDestroy kWindow)
		(super dispose:)
	)
	
	(method (show)
		(ScrollWindow ScrollShow kWindow)
	)
	
	(method (hide)
		(ScrollWindow ScrollHide kWindow)
	)
	
	(method (addString param1)
		(ScrollWindow ScrollAdd kWindow param1 &rest)
	)
	
	(method (modifyString param1)
		(ScrollWindow ScrollModify kWindow param1 &rest)
	)
	
	(method (insertString param1)
		(ScrollWindow ScrollInsert kWindow param1 &rest)
	)
	
	(method (erase)
		(ScrollWindow ScrollClear kWindow)
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
			(+= nsLeft theNsLeft)
			(+= nsRight theNsLeft)
			(+= nsTop theNsTop)
			(+= nsBottom theNsTop)
		else
			(+= nsRight (- theNsLeft nsLeft))
			(+= nsBottom (- theNsTop nsTop))
			(= nsLeft theNsLeft)
			(= nsTop theNsTop)
		)
		(ScrollWindow ScrollResize kWindow self)
	)
	
	(method (resize param1 param2 param3)
		(if (and (> argc 2) param3)
			(+= nsRight param1)
			(+= nsBottom param2)
		else
			(= nsRight (+ nsLeft param1 -1))
			(= nsBottom (+ nsTop param2 -1))
		)
		(ScrollWindow ScrollResize kWindow self)
	)
	
	(method (where param1)
		(ScrollWindow ScrollWhere kWindow param1)
	)
)
