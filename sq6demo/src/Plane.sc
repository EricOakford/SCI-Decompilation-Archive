;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	PLANE.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1993
;;;;
;;;;	Author: 	Brian K. Hughes
;;;;	Updated: 
;;;;
;;;;  A plane is a rectangle in which animation will normally occur
;;;;
;;;;	Classes:
;;;;	 Plane
;;;;


(script# PLANE)
(include game.sh)
(use Main)
(use Styler)
(use DText)
(use Array)
(use Actor)
(use System)


(class Plane kindof Object
	(properties
		resX					UNSET	; resolution of the plane 
		resY					UNSET	; (for scaling)

		left					NULL			;  dimensions of the plane
		top					NULL
		right					NULL
		bottom				NULL

		inLeft				NULL			;  dimensions of the inset rectangle
		inTop					NULL			;		(normally the same as the rectangle)
		inRight				NULL
		inBottom				NULL

		vanishingX			0				;- vanishing point
		vanishingY			0				;/
		coordType			NULL			;  not used by SCI
		picture				-1				;  -1 means no pic associated
		style					SHOW_PLAIN	;	style for drawing plane
		priority				-1
		back					0				;  background default color of plane
		bitmap				0				;  a view used as a bitmap background

		casts					0				;  pointer to list of cast lists
		mirrored				0				;  is plane mirrored?
	)
;;;	(methods
;;;		addCast				; add a new cast list to our casts
;;;		deleteCast			; delete a cast list from our casts
;;;		drawPic				; change the picture and update
;;;		findCast				; find the cast by name
;;;		onMe					; return if an event is on the plane
;;;		posn					; move the plane
;;;		scaleBitmap			; scale the bitmap (if any)
;;;		setBitmap			; use a bitmap as the background
;;;		setInsetRect		; set the inset rect of the plane
;;;		setRect				; set the rect of the plane (and the inset rect)
;;;		setSize				; set the rect according to the size of the items
;;;		setTitle				; set the title of a plane
;;;	)
	
	(method (addCast castList)
		;
		; Add another cast list to our list of casts.

		(casts add:
			(castList
				add:		,
				plane:	self,
				yourself:
			)
		)
	)

	(method (deleteCast castList)
		;
		; Delete a cast list out of our list of casts.

		(casts delete:
			(castList
				plane:	0,
				yourself:
			)
		)
	)

	(method (dispose)
		;
		; Get rid of the plane and plane def.

		(casts
			delete: 	cast,
			dispose:
		)

		(= casts (= bitmap 0))

		(planes delete: self)
		(DeletePlane self)
		(super dispose:)
	)

	(method (drawPic thePic theStyle)
		;
		; Draw a picture on the plane (-1 means erase pic, -2 means erase plane)

		(= picture thePic)
		(if (> argc 1)
			(= style theStyle)
		)

		(= mirrored (if (& style SHOW_MIRRORED) TRUE else FALSE))
		(UpdatePlane self)
		(Styler doit: self style)
	)
	
	(method (findCast theName &tmp node theList)
		;
		; Return the handle to the cast list whose name matches 'theName'

		(for	((= node (List LFirstNode (casts elements?))))
				node
				((= node (casts nextNode?)))
			(casts nextNode: (List LNextNode node))
			(= theList (List LNodeValue node))
			(if (not (String StrCmp (theList name?) theName))
				(return theList)
			)
		)
		(return 0)
	)

	(method (init l t r b il it_ ir ib)
		;
		; Create the plane def.  If dimensions are passed, put those into
		;	the rectangle.  If more that one set is passed, use the second
		;	set as the inset rect.

;¯gtp¯		(AddPlane self)
		(planes add: self)
		(if (== resX UNSET) (= resX screenWidth ) )
		(if (== resY UNSET) (= resY screenHeight) )
		(if argc
			(self setRect: l t r b)
			(if (> argc 4)
				(self setInsetRect: il it_ ir ib)
			)
;¯gtp¯			(UpdatePlane self)
		)
		(AddPlane self)
		(= casts (Set new:))
	)
	
	(method (onMe theObjOrX theY &tmp oX oY)
		;
		; Determine if a point is in the plane

		(if (== argc 1)
			(= oX	(theObjOrX x?))
			(= oY	(theObjOrX y?))
		else
			(= oX	theObjOrX)
			(= oY	theY)
		)
		(return 	(and	(<= left oX right)
							(<= top oY bottom)
					)
		)
	)

	(method (posn x y onPlane &tmp pWidth pHeight l t r b p noCenter)
		;
		; Move the plane to a point on the screen.  If either point is
		;	-1, the plane will be centered in that direction.
		; 'onPlane' indicates the plane relative to which we should position
		;	ourselves.  If 'onPlane' is omitted, the screen coordinates are used.

		(cond

			; Use the plane passed for reference

			((> argc 2)
				(= l (onPlane left?))
				(= t (onPlane top?))
				(= r (onPlane right?))
				(= b (onPlane bottom?))
			)

			; Use the curRoom's plane for reference

			((and curRoom
					(= p (curRoom plane?))
				)
				(= l (p left?))
				(= t (p top?))
				(= r (p right?))
				(= b (p bottom?))
			)

			; Use the screen coordinates for reference

			(else
				(= l 0)
				(= t 0)
				(= r lastScreenX)
				(= b lastScreenY)
			)
		)

		; If -1 was passed in either direction, center on that axis
		(= pWidth (- right left))
		(= pHeight (- bottom top))
		(if (== x -1)
			(= x (+ l (/ (- (- r l) pWidth) 2)))
		else
			(+= x l)
		)
		(if (== y -1)
			(= y (+ t (/ (- (- b t) pHeight) 2)))
		else
			(+= y t)
		)
;¯gtp¯		(self setRect: x y (+ x (+ pWidth 1)) (+ y (+ pHeight 1)))
		(self setRect: x y (+ x pWidth ) (+ y pHeight) )
	)
	
	(method (scaleBitmap sLeft sTop sRight sBottom
								&tmp w1 w2 h1 h2 xs ys scaling l t r b)
		;
		; Scale the bitmap (if any) to the rectangle specified.  If no rectangle
		;	is passed, scale to the size of the plane.

		(if argc
			(= l sLeft)
			(= t sTop)
			(= r sRight)
			(= b sBottom)
		else
			(= l left)
			(= t top)
			(= r right)
			(= b bottom)
		)

		(= w2 (CelWide (bitmap view?) (bitmap loop?) (bitmap cel?)))
		(= h2 (CelHigh (bitmap view?) (bitmap loop?) (bitmap cel?)))

		(= w1 (+ (- r l) 1))
		(= h1 (+ (- b t) 1))
		;;; handle if xs will get bigger than 32000
		(if (< w1 250)
			(= xs (/ (* w1 scaleBase) w2))
		else
			(= xs (* (/ (* (/ w1 2) scaleBase) w2) 2))
		)
		(= ys (/ (* h1 scaleBase) h2))
		(bitmap
			scaleSignal:	scalable,
			scaleX:			xs,
			scaleY:			ys
		)
		(UpdateScreenItem bitmap)
	)
	
	(method (setBitmap v l c scale &tmp newCast w h scaling)
		;
		; Create a separate cast and put the bitmap into it as a screen
		;	item.  if 'scale' is passed and TRUE, the bitmap will be scaled
		;	to fit the plane.  If not, the plane will be sized to fit the
		;	bitmap.

		(= scaling 0) ;initialize the temp

		(if bitmap
			(bitmap dispose:)
			(= newCast (self findCast: {planeBM}))
		else
			((= newCast (Cast new:))
				name: {planeBM}
			)
			(self addCast: newCast)
		)

		(if (< argc 3)
			((= bitmap (v new:))
				posn:		0 0,
				init:		newCast
			)
			(if (> argc 1)
				(= scaling l)
			)
		else
			((= bitmap (View new:))
				view:		v,
				loop:		l,
				cel:		c,
				posn:		0 0,
				init:		newCast
			)
			(if (> argc 3)
				(= scaling scale)
			)
		)

		(= w (CelWide (bitmap view?) (bitmap loop?) (bitmap cel?)))
		(= h (CelHigh (bitmap view?) (bitmap loop?) (bitmap cel?)))
		(if scaling
			(self scaleBitmap:)
		else
			(self setRect: left top (+ left w) (+ top h))
		)
	)
	
	(method (setInsetRect il it_ ir ib)
		(= inLeft il)
		(= inTop it_)
		(= inRight ir)
		(= inBottom ib)
	)

	(method (setRect l t r b doUpdate)
		(= left l)
		(= top t)
		(= right r)
		(= bottom b)
		(self setInsetRect: l t r b)

		; Rescale the bitmap background, if any
		(if (and	bitmap
					(& (bitmap scaleSignal?) scalable)
			)
			(self scaleBitmap:)
		)

		(if (and (> argc 4) doUpdate)
			(UpdatePlane self)
		)
	)
	
	(method (setSize &tmp r b node1 node2 theList obj)
		;
		; Set the rect to the minimum required to encompass all the items
		;	in the plane's cast lists.

		(= r (= b 0))
		(for	((= node1 (List LFirstNode (casts elements?))))
				node1
				((= node1 (casts nextNode?)))
			(casts nextNode: (List LNextNode node1))
			(= theList (List LNodeValue node1))

			(for	((= node2 (List LFirstNode (theList elements?))))
					node2
					((= node2 (theList nextNode?)))
				(theList nextNode: (List LNextNode node2))
				(= obj (List LNodeValue node2))
				(if (> (obj nsRight?) r)
					(= r (obj nsRight?))
				)
				(if (> (obj nsBottom?) b)
					(= b (obj nsBottom?))
				)
			)
		)
		(self setRect: left top (+ left r) (+ top b))
	)
	
	(method (setTitle theText
							&tmp 	t b theCast r th ph
									node1 node2 theList obj txt
									w1 w2 margin)
		;
		; Set a title into the plane.  Titles are 12 pixels high and
		;	a single line of text, white on black.

		(self addCast: ((= theCast (Cast new:)) name: {titleCast}, yourself:))
		((= txt (DText new:))
			;EO: Text color and font customized for SQ6
			fore:		81			;255,
			back:		0,
			font:		userFont	;SYSFONT,
			text:		theText,
			posn:		0 0
		)

		; Calculate the size of the text's nsRect
		(= r (IntArray new:))
		(Text TextSize (r data?) theText userFont -1) ;SYSFONT -1)
		(= th (+ (r at: 3) 2)) ;12))

		(txt
			nsLeft:		0,
			nsTop:		0,
			nsRight:		(- right left),
			nsBottom:	th
		)

		; Calculate the size of the text's textRect & center it within the nsRect
		(= w1 (- (txt nsRight?) (txt nsLeft?)))
		(= w2 (- (r at: 2) (r at: 0)))
		(= margin (/ (- w1 w2) 2))
		(txt
			textLeft:	margin,
			textTop:		2,
			textRight:	(+ margin w2 8),
			textBottom:	th,
			init:			theCast
		)
		(r dispose:)

		; Now adjust the plane by the text height
		(= ph (+ (- bottom top) 1))
		(if (< top th)
			(= top 0)
		else
			(-= top th)
		)
		(self setRect: left top right (+ top ph th) TRUE)

		; Now adjust all the items in the plane down by the title height
		(for	((= node1 (List LFirstNode (casts elements?))))
				node1
				((= node1 (casts nextNode?)))
			(casts nextNode: (List LNextNode node1))
			(= theList (List LNodeValue node1))

			(for	((= node2 (List LFirstNode (theList elements?))))
					node2
					((= node2 (theList nextNode?)))
				(theList nextNode: (List LNextNode node2))
				(= obj (List LNodeValue node2))
				(if (!= obj txt)
					(obj
						y: 			(+ (obj y?) th),
						nsTop:		(+ (obj nsTop?) th),
						nsBottom:	(+ (obj nsBottom?) th)
					)
					(UpdateScreenItem obj)
				)
			)
		)
	)
)