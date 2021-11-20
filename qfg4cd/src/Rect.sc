;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64919)
(include sci.sh)
(use System)


(class Rect of Obj
	(properties
		scratch 0
		left 0
		top 0
		right 0
		bottom 0
	)
	
	(method (init)
		(self set: 0 0 0 0)
		(return self)
	)
	
	(method (with &tmp newSelf)
		(= newSelf (self new:))
		(newSelf set: &rest)
		(return newSelf)
	)
	
	(method (set theTop theLeft theBottom theRight)
		(= left theLeft)
		(= top theTop)
		(= right theRight)
		(= bottom theBottom)
		(return self)
	)
	
	(method (copy param1 &tmp newSelf)
		(if (& -info- $8000)
			(= newSelf (self new:))
		else
			(= newSelf self)
		)
		(newSelf
			set: (param1 top?) (param1 left:) (param1 bottom?) (param1 right:)
		)
	)
	
	(method (moveTo theLeft theTop)
		(= bottom (+ bottom (- theTop top)))
		(= right (+ right (- theLeft left)))
		(= top theTop)
		(= left theLeft)
		(return self)
	)
	
	(method (moveBy param1 param2)
		(self moveTo: (+ left param1) (+ top param2))
	)
	
	(method (center param1)
		(self
			moveTo:
				(+
					(param1 left:)
					(/ (- (param1 width:) (self width:)) 2)
				)
				(+
					(param1 top?)
					(/ (- (param1 height:) (self height:)) 2)
				)
		)
	)
	
	(method (centerIn param1)
		(self
			moveTo:
				(+
					(param1 left:)
					(/ (- (param1 width:) (self width:)) 2)
				)
				(+
					(param1 top?)
					(/ (- (param1 height:) (self height:)) 2)
				)
		)
	)
	
	(method (centerOn param1 param2)
		(self
			moveTo:
				(- param1 (/ (self width:) 2))
				(- param2 (/ (self height:) 2))
		)
	)
	
	(method (width param1)
		(if argc (= right (+ left param1)))
		(return (- right left))
	)
	
	(method (widen param1)
		(= right (+ right param1))
		(self width:)
	)
	
	(method (height param1)
		(if argc (= bottom (+ top param1)))
		(return (- bottom top))
	)
	
	(method (heighten param1)
		(= bottom (+ bottom param1))
		(self width:)
	)
	
	(method (expand param1 param2)
		(= left (- left param1))
		(= top (- top param2))
		(= right (+ right param1))
		(= bottom (+ bottom param2))
		(return self)
	)
	
	(method (contract param1 param2)
		(self expand: (- param1) (- param2))
	)
	
	(method (sizeToStr &tmp theTop theLeft temp2)
		(= theTop top)
		(= theLeft left)
		(self moveTo: theLeft theTop)
		(return self)
	)
	
	(method (sizeToCel)
		(= bottom (+ top (CelHigh &rest)))
		(= right (+ left (CelWide &rest)))
		(return self)
	)
	
	(method (celRect &tmp temp0)
		(= temp0 (if (== self Rect) (self new:) else self))
	)
	
	(method (contains param1 param2)
		(return
			(cond 
				((== argc 2)
					(if
						(and
							(<= top param2)
							(<= param2 (- bottom 1))
							(<= left param1)
						)
						(<= param1 (- right 1))
					)
				)
				(
				(and (param1 respondsTo: #x) (param1 respondsTo: #y)) (self contains: (param1 x?) (param1 y?)))
				(
					(and
						(<= top (param1 top?))
						(>= bottom (param1 bottom?))
						(<= left (param1 left:))
					)
					(>= right (param1 right:))
				)
			)
		)
	)
	
	(method (intersects param1)
		(return
			(not
				(if
					(or
						(< (param1 right:) left)
						(< (param1 bottom?) top)
						(> (param1 left:) right)
					)
				else
					(> (param1 top?) bottom)
				)
			)
		)
	)
	
	(method (intersection param1)
		(if (self intersects: param1)
			(= top (Max top (param1 top?)))
			(= left (Max left (param1 left:)))
			(= bottom (Min bottom (param1 bottom?)))
			(= right (Min right (param1 right:)))
		else
			(self init:)
		)
		(return self)
	)
	
	(method (union param1)
		(= top (Min top (param1 top?)))
		(= left (Min left (param1 left:)))
		(= bottom (Max bottom (param1 bottom?)))
		(= right (Max right (param1 right:)))
		(return self)
	)
	
	(method (isEmpty)
		(return (if (== top bottom) (== left right) else 0))
	)
	
	(method (mousedOn param1 param2 param3 &tmp temp0)
		(= temp0 (if (== argc 1) 1 else param2))
		(cond 
			((!= (param1 type?) temp0) 0)
			(
				(and
					(>= argc 3)
					param3
					(== (& (param1 modifiers?) param3) 0)
				)
				0
			)
			(else (self contains: (param1 x?) (param1 y?)))
		)
	)
	
	(method (keyIn)
	)
	
	(method (howFar param1 &tmp temp0 temp1 rectLeft rectTop)
		(switch param1
			(0
				(= temp0 [param1 1])
				(= temp1 [param1 2])
				(= rectLeft (self midPixel: 1))
				(= rectTop (self midPixel: 2))
			)
			(-1
				(= temp0 ([param1 1] left:))
				(= temp1 ([param1 1] top?))
				(= rectLeft (self left:))
				(= rectTop (self top?))
			)
			(else 
				(= temp0 (param1 midPixel: 1))
				(= temp1 (param1 midPixel: 2))
				(= rectLeft (self midPixel: 1))
				(= rectTop (self midPixel: 2))
			)
		)
		(GetDistance rectLeft rectTop temp0 temp1)
	)
	
	(method (midPixel param1)
		(return
			(switch param1
				(1
					(return (/ (+ left right) 2))
				)
				(78
					(return (/ (- right left) 2))
				)
				(2
					(return (/ (+ top bottom) 2))
				)
				(206
					(return (/ (- bottom top) 2))
				)
				(else  (return 0))
			)
		)
	)
)
