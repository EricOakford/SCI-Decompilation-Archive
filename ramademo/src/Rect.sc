;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	RECT.SC
;;;;	(c) Sierra On-Line, Inc, 1989
;;;;
;;;;	Author: Jeff Stephenson
;;;;
;;;;	The Rectangle class.
;;;;
;;;;	Classes:
;;;;		Rectangle


(script#	RECT)
(include game.sh)
(use System)


(class Rectangle of Object
;;; The Rectangle class implements operations on a rectangular region
;;; defined by its top, bottom, left, and right coordinates.  The lines
;;; describing a rectangle are defined as passing BETWEEN the points of the
;;; coordinate system and having on the coordinates of the points to the
;;; right or below the line.  Thus, the rectangle (0, 0, 10, 10) contains
;;; the points (0, 0), (0, 9), (9, 0), and (9, 9) but not the points (0, 10),
;;; (10, 0), or (10, 10).  See "Inside Macintosh" for more discussion of
;;; the definition of rectangles.
;;;
;;; The top-left corner of a Rectangle is considered to be its origin, the
;;; point which is moved to a spot by the movement methods and which stays
;;; anchored when the width or height of the Rectangle is set.


	(properties
		name		"Rect"
		left		0
		top		0
		right		0
		bottom	0
	)

;;;	(methods
;;;		with
;;;		set
;;;		copy
;;;
;;;		moveTo
;;;		moveBy
;;;		center
;;;		centerIn
;;;		centerOn
;;;
;;;		width
;;;		widen
;;;		height
;;;		heighten
;;;		expand
;;;		contract
;;;		sizeToStr
;;;		sizeToCel
;;;		celRect
;;;
;;;		contains
;;;		intersects
;;;		intersection
;;;		union
;;;		isEmpty
;;;		mousedOn
;;;		keyIn				; invoked when key enters rectangle via a KeyCursor
;;;		howFar
;;;		midPixel
;;;	)



	(method (with &tmp rect)
	;; Return a new rectangle with the given coordinates.
	;;
	;; Example:
	;;	(Rectangle with: theTop theLeft theBottom theRight)

		(= rect (self new:))
		(rect set: &rest)
		(return rect)
	)


	(method (set t l b r)
	;; Return the receiver with new coordinates.
	;;
	;; Example:
	;;	(myRect set: theTop theLeft theBottom theRight)

		(= left l)
		(= top t)
		(= right r)
		(= bottom b)
		(return self)
	)


	(method (init)
	;; Initialize the receiver to an empty rectangle at (0, 0).

		(self set: 0 0 0 0)
		(return self)
	)


	(method (copy r &tmp dest)
	;; Return a copy of the rectangle r.  If the receiver is the class Rect,
	;; return a new rectangle.  Otherwise, copy the coordinates of r into
	;; the receiver and return it.
	;;
	;; Example:
	;;	(= newRect (Rectangle copy: myRect))
	;;	(myRect copy: anotherRect)

		(if (& -info- CLASS)
			(= dest (self new:))
		else
			(= dest self)
		)
		(return
			(dest set: (r top?) (r left?) (r bottom?) (r right?))
		)
	)


	(method (moveTo x y)
	;; Move the Rectangle's top-left corner to (x, y).
	;;
	;; Example:
	;;	(myRect moveTo: 10 20)

		(+= bottom (- y top))
		(+= right (- x left))
		(= top y)
		(= left x)
		(return self)
	)


	(method (moveBy dx dy)
	;; Move the top-left corner of the Rectangle by (dx, dy).
	;;
	;; Example:
	;;	(myRect moveBy: -5 -5)

		(return
			(self moveTo: (+ left dx) (+ top dy))
		)
	)

	(method (center r)
	;; Center the receiver in the Rectangle r.
	;;	(Alias for centerIn)
	;;
	;; Example:
	;;	(windowRect centerIn: screenRect)

		(self moveTo:
			(+ (r left?) (/ (- (r width:) (self width:)) 2))
			(+ (r top?) (/ (- (r height:) (self height:)) 2))
		)
	)



	(method (centerIn r)
	;; Center the receiver in the Rectangle r.
	;;
	;; Example:
	;;	(windowRect centerIn: screenRect)

		(self moveTo:
			(+ (r left?) (/ (- (r width:) (self width:)) 2))
			(+ (r top?) (/ (- (r height:) (self height:)) 2))
		)
	)


	(method (centerOn cx cy)
	;; Place the center of the receiver at the point (cx, cy)
	;;
	;; Example:
	;;	(windowRect centerIn: screenRect)

		(self moveTo:
			(- cx (/ (self width:) 2))
			(- cy (/ (self height:) 2))
		)
	)


	(method (width w)
	;; If w is specified, set the width of the Rectangle to w.  Return
	;; the width of the rectangle.
	;;
	;; Example:
	;;	(= myRect ((Rect new:) top: 10 left: 10 width:20 height:30))
	;;	(= theWidth (myRect width:))

		(if argc
			(= right (+ left w))
		)
		(return (- right left))
	)


	(method (widen dw)
	;; Increase the width of the receiver by dw.
	;;
	;; Example:
	;;	(myRect widen: 5)

		(+= right dw)
		(return (self width:))
	)


	(method (height h)
	;; If h is specified, set the height of the Rectangle to h.  Return
	;; the height of the rectangle.
	;;
	;; Example:
	;;	(= myRect ((Rect new:) top: 10 left: 10 width:20 height:30))
	;;	(= theHeight (myRect height:))

		(if argc
			(= bottom (+ top h))
		)
		(return (- bottom top))
	)


	(method (heighten dh)
	;; Increase the height of the receiver by dh.
	;;
	;; Example:
	;;	(myRect heighten: -5)

		(+= bottom dh)
		(return (self width:))
	)


	(method (expand dx dy)
	;; Expand the Rectangle by (dx, dy).  This moves each edge of the Rectangle
	;; by the given amounts.
	;;
	;; Example:
	;;	(myRect expand: 5 5)

		(-= left dx)
		(-= top dy)
		(+= right dx)
		(+= bottom dy)
		(return self)
	)


	(method (contract dx dy)
	;; Contract the Rectangle by (dx, dy).  This is just expand:ing with
	;; negative arguments, but saves you the trouble (and code size) of
	;; negating those arguments.
	;;
	;; Example:
	;;	(myRect contract: 5 5)

		(return (self expand: (- dx) (- dy)))
	)


	(method (contains r yc)
	;; Returns TRUE if the receiver contains the arguments, FALSE otherwise.
	;; This may be called in three ways:
	;;
	;;	(myRect contains: x y)
	;;			Returns TRUE if the point (x, y) is inside the receiver.
	;;
	;;	(myRect contains: anObject)
	;;			Returns TRUE if anObject has x and y properties and the
	;;			values are a point within the receiver.
	;;
	;;	(myRect contains: anotherRect)
	;;			Returns TRUE if no part of anotherRect is outside the receiver.

;		(define	xc	r)
		(return
			(cond
				((== argc 2)
					(and
						(<= top yc (- bottom 1))
						(<= left r (- right 1))
					)
				)
				((and (r respondsTo: #x:) (r respondsTo: #y:))
					(self contains: (r x?) (r y?))
				)
				(else
					(and
						(<= top (r top?))
						(>= bottom (r bottom?))
						(<= left (r left?))
						(>= right (r right?))
					)
				)
			)
		)
	)


	(method (intersects r)
	;; Returns TRUE if r intersects the receiver, FALSE otherwise.
	;;
	;; Example:
	;;	(myRect intersects: anotherRect)

		(return
			(not
				(or
					(< (r right?) left)
					(< (r bottom?) top)
					(> (r left?) right)
					(> (r top?) bottom)
				)
			)
		)
	)


	(method (intersection r)
	;; Modifies the receiver to be the rectangle which is the intersection
	;; of the receiver and r.
	;;
	;; Example:
	;;	(= updateRect (myRect intersection: dialogRect))
	;;
	;; If you don't want to modify the receiver, create a copy of it
	;; in order to get the intersection:
	;;	((myRect new:) intersection: dialogRect)

		(if (self intersects: r)
			(= top (Max top (r top?)))
			(= left (Max left (r left?)))
			(= bottom (Min bottom (r bottom?)))
			(= right (Min right (r right?)))
		else
			(self init:)
		)
		(return self)
	)


	(method (union r)
	;; Modifies the receiver to be the smallest rectangle which contains
	;; both the receiver and r.  As in intersection:, create a copy of the
	;; receiver if you don't want it modified.
	;;
	;; Example:
	;;	((nowSeen new:) union: lastSeen)

		(= top (Min top (r top?)))
		(= left (Min left (r left?)))
		(= bottom (Max bottom (r bottom?)))
		(= right (Max right (r right?)))
		(return self)
	)


	(method (isEmpty)
	;; Returns TRUE if no points are contained within the receiver,
	;; FALSE otherwise.
	;;
	;; Example:
	;;	(if (myRect isEmpty:) ...)

		(return
			(and
				(== top bottom)
				(== left right)
			)
		)
	)


	(method (sizeToStr &tmp t l r)
	;; Arguments are:
	;;		string, font, width
	;; Size the receiver so that it will enclose the passed string in
	;; the given font.  width should be -1 for no word wrap, 0 for the
	;; default word wrap, and a given length for the specified width.
	;;
	;; Example:
	;;		(editItem sizeToStr: "This is a test." myFont)

		(= t top)							;save our location
		(= l left)
;		(KText TextSize self &rest)			;get the size of the text rectangle
		(self moveTo: l t)				;move back to our location
		(return self)
	)


	(method (sizeToCel)
	;; Arguments are:
	;;		view loop cel
	;; Size the receiver so that it will enclose the specified cel.
	;;
	;; Example:
	;;		(iconRect sizeToCel: vEgoRunning lFacingLeft 0)

		(= bottom (+ top (CelHigh &rest)))
		(= right (+ left (CelWide &rest)))
		(return self)
	)


	(method (celRect &tmp r)
	;;	Arguments are:
	;;		view loop cel x y z
	;; Return the rectangle which encloses the (view, loop, cel) view positioned
	;;	at (x, y, z)

		(= r
			(if (== self Rectangle)
				(self new:)
			else
				self
			)
		)
;NA		(CelRect r &rest)
		(return r)
	)


	(method (mousedOn event evtType theMods &tmp t)
	;; Return TRUE if 'event' is a mouseDown within the receiver rectangle.
	;; If 'theMods' is passed, the event must have the specified modifiers.
	;;
	;; Example:
	;;		(if (activeRect mousedOn: event shiftDown) ... )

		
		(= t (if (== argc 1) mouseDown else evtType))

		(return
			(cond
				((!= (event type?) t)
					FALSE
				)
				((and
					(>= argc 3)
					theMods
					(== (& (event modifiers?) theMods) 0)
				 )
					FALSE
				)
				(else
					(self contains: (event x?) (event y?))
				)
			)
		)
	)

	;; 3/27/91 Dave Slayback and Bob Heitman
	;; Invoked when the cursor is moved to the rectangle via
	;; a KeyCursor call.  It is intended to allow objects of this
	;; hierarchy to respond prior to any actual selection
	;;
	(method (keyIn)
	)

	(method (howFar targetRect &tmp targetX targetY startX startY)
	;;
	;;	Distance to another rectangle, or if NULLID is given as the first
	;;	parm, use the X and Y passed as the next two parms. If you want the
	;;	distance between the upper left corners of the two rectangles instead
	;; of the centers, use -1 as the first parameter and pass the target
	;;	rect as the second.
	;;
		(switch targetRect
			(NULL
				(= targetX [targetRect 1])
				(= targetY [targetRect 2])
				(= startX  (self midPixel: #x))
				(= startY  (self midPixel: #y))
			)
			(-1
				(= targetX ([targetRect 1] left?))
				(= targetY ([targetRect 1] top?))
				(= startX (self left?))
				(= startY (self top?))
			)
			(else
				(= targetX (targetRect midPixel: #x))
				(= targetY (targetRect midPixel: #y))
				(= startX  (self midPixel: #x))
				(= startY  (self midPixel: #y))
			)
		)
		(return
			(GetDistance
				startX startY targetX targetY
			)
		)
	)

	(method (midPixel axis)
	;
	;	Returns the mid pixel point of a rectangle; horizontal or vertical
	;
		(switch axis
			(#x
				(return (/ (+ left right) 2))
			)
			(#width
				(return (/ (- right left) 2))
			)
			(#y
				(return (/ (+ top bottom) 2))
			)
			(#height
				(return (/ (- bottom top) 2))
			)
			(else
				(return 0)
			)
		)
	)
)
