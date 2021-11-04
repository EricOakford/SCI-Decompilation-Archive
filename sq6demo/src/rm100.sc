;;; Sierra Script 1.0 - (do not remove this comment)
(script# 100)
(include game.sh)
(use Main)
(use SQRoom)
(use DText)
(use String)
(use Print)
(use System)

(public
	rm100 0
)

(local
	str
	newDText
	newDText_2
	newDText_3
	newDText_4
	newDText_5
	newDText_6
)
(procedure (localproc_00af param1)
	((= newDText (DText new:))
		text: (KString StrDup (str data?))
		fore: 0
		font: 60
		mode: teJustCenter
		posn: 6 (+ param1 1)
		setPri: 254
		setSize: 305
		init:
	)
	((= newDText_2 (DText new:))
		text: (KString StrDup (str data?))
		fore: 255
		font: 60
		mode: teJustCenter
		posn: 5 param1
		setPri: 255
		setSize: 305
		init:
	)
	(str dispose:)
)

(instance rm100 of SQRoom
	(properties)
	
	(method (init)
		(Print fore: 84 back: 86)
		(theIconBar hide: TRUE disable:)
		((theIconBar plane?) priority: -10)
		((showTitle plane?) priority: -10)
		(UpdatePlane (theIconBar plane?))
		(UpdatePlane (showTitle plane?))
		(super init:)
		(Bset fInIntro)
		(curRoom setScript: sIntroScript)
	)
	
	(method (dispose)
		((curRoom plane?) setRect: 0 0 319 138)
		(super dispose:)
	)
)

(instance sIntroScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame setCursor: waitCursor TRUE)
				(= cycles 1)
			)
			(1
				(Palette PalIntensity 0 256 100)
				(theMusic number: 104 flags: 1 loop: -1 setVol: 127 play:)
				(= ticks 120)
			)
			(2
				((curRoom plane?) setRect: 0 0 320 200 priority: 10)
				(FrameOut)
				(curRoom drawPic: 100)
				(= ticks 330)
			)
			(3
				(keyDownHandler addToFront: self)
				(mouseDownHandler addToFront: self)
				(= str
					(String
						format: {Space Quest 6: The Spinal Frontier \nInteractive Demo}
					)
				)
				((= newDText_3 (DText new:))
					text: (KString StrDup (str data?))
					fore: 0
					font: 60
					posn: 61 11
					setPri: 254
					setSize: 200
					mode: teJustCenter
					init:
				)
				((= newDText_4 (DText new:))
					text: (KString StrDup (str data?))
					fore: 85
					font: 60
					posn: 60 10
					setPri: 255
					setSize: 200
					mode: teJustCenter
					init:
				)
				(= str
					(String
						format: {To order, see your local retailer\nor call:\n1-800-757-7707}
					)
				)
				((= newDText_5 (DText new:))
					text: (KString StrDup (str data?))
					fore: 0
					font: 60
					posn: 69 166
					setPri: 254
					setSize: 200
					mode: teJustCenter
					init:
				)
				((= newDText_6 (DText new:))
					text: (KString StrDup (str data?))
					fore: 85
					font: 60
					posn: 68 165
					setPri: 255
					setSize: 200
					mode: teJustCenter
					init:
				)
				(= ticks 700)
			)
			(4
				(= str
					(String
						format:
							{Through a series of events too humiliating to explain in a mere demo,
							Roger Wilco once again finds himself doing those duties for which he seems 
							to have been born and misbred - cleaning up after the rest of the universe. 
							As we begin our demo we find Roger floating outside the main viewscreen of the Deepship 86, 
							the ship to which Roger is currently assigned. While afloat squeegying micrognats and comet entrails away, 
							Deepship 86 is approached by a most unusual craft. As is so often the case, Roger goes unnoticed and is witness 
							to what happens to his crewmates.
							}
					)
				)
				(localproc_00af 50)
				(= ticks 1700)
			)
			(5
				(newDText_2 dispose:)
				(newDText dispose:)
				(= str
					(String
						format:
							{Odd characters materialize on the bridge and convert the crew to small dessert-type spheres of some sort. 
							After watching carefully - and nearly soiling his suit - he ventures inside, and not because he's so brave. 
							He only has so much oxygen. How will he save his sorry carcass this time? \n\nWhile playing our interactive demo, 
							use icons to select functions and cursor placement to make things happen. There are several puzzles to be solved. 
							If you get bored, select the CONTROL button and from there, select QUIT. But you'd better not!
							}
					)
				)
				(localproc_00af 45)
				(= ticks 1700)
			)
			(6
				(keyDownHandler delete: self)
				(mouseDownHandler delete: self)
				(newDText_2 dispose:)
				(newDText dispose:)
				(newDText_4 dispose:)
				(newDText_3 dispose:)
				(newDText_5 dispose:)
				(newDText_6 dispose:)
				(theMusic fade: 0 20 8 1)
				(= ticks 240)
			)
			(7
				(curRoom drawPic: -1)
				(= cycles 20)
			)
			(8
				(curRoom newRoom: 360)
				(self dispose:)
			)
		)
	)
	
	(method (handleEvent event)
		(if (!= (event type?) mouseUp)
			(= ticks 0)
			(self changeState: (+ state 1))
		)
	)
)
