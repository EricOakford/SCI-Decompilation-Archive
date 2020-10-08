;;; Sierra Script 1.0 - (do not remove this comment)
(script# ANM)
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
				(if (> argc 4)
					(= maxItems [theMaxItems 4])
				)
			)
		)
		(if (not plane)
			(= plane (Plane new:))
		)
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
	
	(method (addString theString)
		(ScrollWindow ScrollAdd kWindow theString &rest)
	)
	
	(method (modifyString theString)
		(ScrollWindow ScrollModify kWindow theString &rest)
	)
	
	(method (insertString theString)
		(ScrollWindow ScrollInsert kWindow theString &rest)
	)
	
	(method (erase)
		(ScrollWindow ScrollClear kWindow)
	)
	
	(method (scrollTo param1 param2 param3)
		(switch param1
			(0
				(ScrollWindow ScrollHome kWindow)
			)
			(1
				(ScrollWindow ScrollPageUp kWindow)
			)
			(2
				(ScrollWindow ScrollUpArrow kWindow)
			)
			(3
				(ScrollWindow ScrollGo kWindow param2 param3)
			)
			(4
				(ScrollWindow ScrollDownArrow kWindow)
			)
			(5
				(ScrollWindow ScrollPageDown kWindow)
			)
			(6
				(ScrollWindow ScrollEnd kWindow)
			)
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
		(ScrollWindow ScrollResize kWindow self)
	)
	
	(method (resize param1 param2 param3)
		(if (and (> argc 2) param3)
			(= nsRight (+ nsRight param1))
			(= nsBottom (+ nsBottom param2))
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
