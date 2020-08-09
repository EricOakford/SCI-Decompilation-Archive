;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	WRITEFTR.SC
;;;;	(c) Sierra On-Line, Inc, 1990
;;;;
;;;;	Author: J.Mark Hood
;;;;
;;;;	Simple C.A.S.E. Tools for positioning and adding response to
;;;;	Features, Views, Props, Actors and their subclasses.
;;;;
;;;;	Last Updated:
;;;;		Brian K. Hughes
;;;;		February 14, 1992
;;;;
;;;;	Classes:
;;;;		WriteCode
;;;;		CreateObject


(script# WRITEFTR)
(include game.sh)
(use Main)
(use Intrface)
(use Print)
(use Dialog)
(use Feature)
(use Window)
(use File)
(use Actor)
(use System)


(define	ITEMSIZE 18)
(define	NUMITEMS 6)


(local
	[nameString	50]
	[nounStr		30]
	[vDoStr		100]
	[vLookStr	100]
	[vTalkStr	100]
	verbString	= [
						{_WALK____________}
						{_LOOK____________}
						{_DO______________}
						{_TALK____________}
						{_ASK_____________}
						{_HELP____________}
						{}
					  ]
	toScreen		= FALSE
	drawNSRect 	= TRUE
	inited		= FALSE
	theType 		= FALSE
	hasDoVerb	= FALSE
	oldWindow
	file
) 

;;;(procedure
;;;	DoProperties
;;;	DoFeature
;;;	DoView
;;;	DoApproach
;;;	DoDoVerb
;;;	Logit
;;;)

(enum
	exitPrint
	makeFeature
	makeView
	makeProp
	makeActor
)


;ษอออออออออออออออป
;บ               บ
;บ Create Object บ
;บ               บ
;ศอออออออออออออออผ

(class CreateObject ;kindof RootObj
;;;	(methods
;;;		doit
;;;	)
	
	(method (doit &tmp [thePath 15] theObj event)
		(theGame setCursor: ARROW_CURSOR)
		(= oldWindow systemWindow)
		(= systemWindow wfWin)
		(if (not inited)
			(= thePath 0)
			(Format @thePath {%d.fea} (curRoom curPic?))	; default "pic#.fea"
			(if (not (GetInput @thePath 30 {Enter path and filename}))
				(return)
			else
				(Format @sysLogPath @thePath)
				(= drawNSRect
					(Print
						addText:		{Outline Features?},
						addTitle:	{Feature Write V1.0},
						addButton:	TRUE {YES} 0 12,
						addButton:	FALSE {NO} 50 12,
						init:
					)
				)
				(= toScreen
					(Print
						addText:		{Display code to screen? (but not doVerb)},
						addTitle:	{Feature Write V1.0},
						addButton:	FALSE {NO} 0 18,
						addButton:	TRUE {YES} 50 18,
						init:
					)
				)
				(= inited TRUE)
			)
		)

		(= theType
			(Print
				addText:		{Class?},
				addTitle:	{Feature Writer V1.0},
				addButton:	Feature 	{Feature} 0 12,
				addButton:	View		{View} 73 12,
				addButton:	Prop		{Prop} 113 12,
				addButton:	Actor		{Actor} 153 12,
				init:
			)
		)
		(if (not theType)
			(return)
		)

		(= theObj (theType new:))

		(= nameString 0)
		(GetInput @nameString 30 {Name?})

		(= nounStr 0)
		(GetInput @nounStr 16 {Noun?})

		(DoProperties theObj)

		(if (== theType Feature)	;makeFeature)
			(DoFeature theObj)
		else
			(DoView theObj)
		)

;CLG		(if (theObj _approachVerbs?)
			(DoApproach theObj)
;CLG		)

		(if
			(Print
				addText:		{Z property},
		 		addTitle:	{Feature Writer V1.0},
		 		addButton:	FALSE {NO} 0 12,
		 		addButton:	TRUE {YES} 50 12,
				init:
			)
			(Print 
				addText:		{Click mouse on object's projection},
				addText:		{onto the ground} 0 12,
				init:
			)
			(while (!= ((= event (Event new:)) type?) mouseDown)
				(event dispose:)
			)
			(GlobalToLocal event)
			(theObj z: (- (event y?) (theObj y?)))
			(theObj y: (event y?))
			(event dispose:)
		)

		(DoDoVerb)

		(WriteCode doit: theObj)

		(= systemWindow oldWindow)
	)
)


;ษออออออออออออป
;บ            บ
;บ Procedures บ
;บ            บ
;ศออออออออออออผ

(procedure (DoProperties obj)
	(obj
		sightAngle:			(GetNumber {sight angle?} 40)
;		_approachVerbs:	(approachD init:)
	)
)


(procedure (DoFeature obj &tmp event theX theY theTop theLeft theBottom theRight)
	(Prints {Click left mouse button on top left corner})
	(while (!= ((= event (Event new:)) type?) mouseDown)
		(event dispose:)
	)
	(GlobalToLocal event)
	(= theTop (event y?))
	(= theLeft (event x?))
	(event dispose:)

	(Prints {Click left mouse button on bottom right corner})
	(while (!= ((= event (Event new:)) type?)	mouseDown)
		(event dispose:)
	)
	(GlobalToLocal event)
	(= theBottom 	(event y?))
	(= theRight 	(event x?))
	(event dispose:)


;  Click on x,y (right-click defaults to center)


	(= theX	(+ (/ (- theRight theLeft) 2) theLeft))
	(= theY	(+ (/ (- theBottom theTop) 2) theTop))

	(obj
		x:				theX,
		y:				theY,
		nsLeft:		theLeft,
		nsTop:		theTop,
		nsBottom:	theBottom,
		nsRight:		theRight
	)

	(if drawNSRect
		(Graph GDrawLine theTop    theLeft 	 	theTop 		theRight 	VMAP 0)
		(Graph GDrawLine theBottom theLeft 		theBottom	theRight 	VMAP 0)
		(Graph GDrawLine theTop		theLeft 	  	theBottom 	theLeft 		VMAP 0)
		(Graph GDrawLine theTop		theRight   	theBottom 	theRight 	VMAP 0)
		(Graph GShowBits theTop 	theLeft 		(+ theBottom 1)	(+ theRight 1)	VMAP)
	)
)


(procedure (DoView obj &tmp event)
	(obj
		view:			(GetNumber {View?} (curRoom curPic?)),
		loop:			(GetNumber {Loop?} 0),
		cel:			(GetNumber {Cel?} 0),
		signal:		(| fixPriOn ignrAct),
		priority:	15,
		init:
	)
	(if (obj respondsTo: #illegalBits)
		(obj illegalBits: 0)
	)
	(while (!= ((= event (Event new:)) type?) mouseDown)
		(GlobalToLocal event)
		(obj posn: (event x?) (event y?))
		(Animate (cast elements?) FALSE)
		(event dispose:)
	)
 	(event dispose:)
)


(procedure (DoApproach obj &tmp event eX eY [str 10] ad)

	; Set up the approachX and approachY

	(if 
		(Print
			addText:		{Where should the approach point be?},
			addButton:	TRUE 	{Select with mouse} 0 20,
			addButton:	FALSE {Default to x, y} 0 34,
			init:
		)
		(while (!= ((= event (Event new:)) type?) mouseDown)
			(event dispose:)
		)
		(GlobalToLocal event)
		(obj
			approachX: (= eX (event x?)),
			approachY: (= eY (event y?))
		)
 		(event dispose:)
		(Graph GDrawLine (- eY 1) 	(- eX 1) (- eY 1) (+ eX 1) 7)
		(Graph GDrawLine eY			(- eX 1) eY			(+ eX 1) 7)
		(Graph GDrawLine (+ eY 1)	(- eX 1) (+ eY 1) (+ eX 1) 7)
		(Graph GDrawLine eY eX eY eX 0)
		(Graph GShowBits (- eY 1) (- eX 1) (+ eY 2) (+ eX 2) VMAP)
	else
		(obj
			approachX:	(obj x?),
			approachY:	(obj y?)
		)
	)

	; Set the approach distance

	(= str 0)
	(= ad
		(Print
			addText:		{How far away must ego} 0 1,
			addText:		{be before he tries to approach?} 0 12
			addEdit:		@str	5	-50 13
			addButton:	TRUE 	{Select with mouse} 0 32
			addButton:	FALSE {Always approach} 0 45,
			init:
		)
	)
	(cond
		(str
			(obj approachDist: (ReadNumber @str))
		)
		((not ad)
			(obj approachDist: 0)
		)
		(else
			(while (!= ((= event (Event new:)) type?) mouseDown)
				(event dispose:)
			)
			(GlobalToLocal event)
			(= eX (event x?))
			(= eY (event y?))
			(obj approachDist: (GetDistance (obj x?) (obj y?) eX eY))
 			(event dispose:)
			(Graph GDrawLine (- eY 1) 	(- eX 1) (- eY 1) (+ eX 1) 28)
			(Graph GDrawLine eY			(- eX 1) eY			(+ eX 1) 28)
			(Graph GDrawLine (+ eY 1)	(- eX 1) (+ eY 1) (+ eX 1) 28)
			(Graph GDrawLine eY eX eY eX 52)
			(Graph GShowBits (- eY 1) (- eX 1) (+ eY 2) (+ eX 2) VMAP)
		)
	)
)


(procedure (DoDoVerb)
	(= hasDoVerb
		(Print
			addText:		{doVerb method?},
			addButton:	TRUE 	{YES} 0 12,
			addButton: 	FALSE {NO} 50 12,
			init:
		)
	)
)


(procedure (Logit what)
	(file
		name:	 			@sysLogPath,
		writeString: 	what,
		close:
	)
)


;ษออออออออออออป
;บ            บ
;บ Write Code บ
;บ            บ
;ศออออออออออออผ

(class WriteCode ;of RootObj

;;;	(methods
;;;		doit
;;;		writeList
;;;	)

	(method 	(doit theObj &tmp [buffer 400] [vlcOrNsStr 40]
										[apStr 50] fileMode rc [str 60] [shmStr 40]
										[shmPath 12] theNoun i j [nounNum 20]
										newFeat temp
				)

		(if (FileIO fileExists @sysLogPath)
			(Format @str {The file '%s' already exists} @sysLogPath)
			(= rc
				(Print
					addText:		@str,
					addButton: 	1 {Replace} 0 20,
					addButton: 	2 {Append} 73 20,
					addButton: 	0 {Cancel} 133 20,
					init:
				)
			)
			(if (not rc)
				(return 0)
			)
		)
		(= fileMode (if (== rc 1) fTrunc else fAppend))
		(if (not
				((= file (File new:))
					name:	@sysLogPath,
					open:	fileMode
				)
			)
			(Format @buffer {Error opening '%s'} @sysLogPath)
			(Prints @buffer)
			(file dispose:)
			(return 0)
		)


		(= buffer 0)

		; Format page 1 - the properties
		(if (theObj isMemberOf: Feature)
			(Format @vlcOrNsStr
				{
					\09\09nsLeft\09\09\09%d\r
				 	\09\09nsTop\09\09\09\09%d\r
				 	\09\09nsBottom\09\09\09%d\r
				 	\09\09nsRight\09\09\09%d\r
				}
				(theObj nsLeft?)
				(theObj nsTop?)
				(theObj nsBottom?)
				(theObj nsRight?)
			)
			(Format @shmPath {..\\msg\\%d.shm} (curRoom curPic?))
			(shmFile name: @shmPath)
			(if (not (shmFile open: fRead))
				(= theNoun 0)
			else
;
;     It's a pain, but it works.
;		 First, convert the noun inputted by the user to upper case.
;
				(for ((= i 0)) (<= i (StrLen @nounStr)) ((++ i))
					(if (< 96 (StrAt @nounStr i) 123)
						(= temp (- (StrAt @nounStr i) 32))
						(StrAt @nounStr i temp)
					)
				)
				(= theNoun 0)
;
;		Read the file (one line at a time
;
            (while (!= (FileIO fileFGets @shmStr 80 (shmFile handle?)) -1)
;
;		Look for "(define". Should be start of nouns in the file.
;
               (if (not (StrCmp @shmStr {(define} 6)) ;returns 0 when they compare
;
; 		Shift string over since we don't have a mid string compare.
;
                  (for ((= i 0)) (<= i 40) ((++ i))
                     (= [shmStr i] [shmStr (+ i 4)])
                  )
;
;		Compare noun in file vs. one inputted.
;
                  (if (not (StrCmp @shmStr @nounStr (StrLen @nounStr)))
                     (= j 0)
;
;    Shift string again to get past noun.
;
                     (for ((= i (+ (/ (StrLen @nounStr) 2) 1))) (< i 20) ((++ i))
                        (= [nounNum j] [shmStr i])
                        (++ j)
                     )
;
;		Read the number at the end of the string read from the file.
;
                     (= theNoun (ReadNumber @nounNum))
                     (break)
                  )
               )
;
;    If we find "; CASES" then we are past the nouns.
;
               (if (not (StrCmp @shmStr {; CASES}))
                  (break)
               )
            )
				(shmFile close:)
			)
;
;		Create a new feature, and add all properties input by user.
;
			(= newFeat (Feature new:))
			(newFeat
            init:,
				setName:		@nameString,
		   	nsLeft:	(theObj nsLeft?),
			   nsTop:	(theObj nsTop?),
			  	nsBottom: (theObj nsBottom?),
			  	nsRight:	(theObj nsRight?),
			  	x:			(theObj x?),
		   	y:			(theObj y?),
		   	z:			(theObj z?),
		   	heading:	(theObj heading?),
		   	sightAngle:	(theObj sightAngle?),
			   approachX:	(theObj approachX?),
		   	approachY:	(theObj approachY?),
;KR	   		approachVerbs: (theObj _approachVerbs?),
            noun:    theNoun
   		)
		else
			(Format @vlcOrNsStr
				{
					\09\09view\09\09\09%d\r
				 	\09\09loop\09\09\09%d\r
				 	\09\09cel\09\09\09%d\r
				}
				(theObj view?)
				(theObj loop?)
				(theObj cel?)
			)
		)

;CLG		(if (theObj _approachVerbs?)
			(Format @apStr
				{
					\09\09approachX\09\09%d\r
					\09\09approachY\09\09%d\r
					\09\09approachDist\09%d\r
					\09\09\_approachVerbs\09$%x\r
				}
				(theObj approachX?)
				(theObj approachY?)
				(theObj approachDist?)
				(theObj _approachVerbs?)
			)
;CLG		else
;CLG			(= apStr 0)
;CLG		)

		(Format @buffer
			{
				\r(instance %s of %s\r
				\09(properties\r
				\09\09x\09\09\09\09\09%d\r
				\09\09y\09\09\09\09\09%d\r
				\09\09z\09\09\09\09\09%d\r
				\09\09heading\09\09\09%d\r
				%s
				\09\09sightAngle\09\09%d\r
				%s
				\09\09noun\09\09\09\09%s\r
				\09)\r
			}
			@nameString
			((theObj -super-?) name?)
			(theObj x?)
			(theObj y?)
			(theObj z?)
			(theObj heading?)
			@vlcOrNsStr
			(theObj sightAngle?)
			@apStr
			@nounStr
		)


		; Display page 1

		(if toScreen
			(Print
				font:			999,
				addText:		@buffer,
				addTitle:	{Feature Writer V1.0},
				init:
			)
		)

		; Write page 1

		(Logit @buffer)



		; Format page 2 - the doVerb (if any)

		(if hasDoVerb
			(Format @buffer
				{
					\09(method (doVerb theVerb)\r
					\09\09(switch theVerb\r
				}
			)
			(Logit @buffer)

			(if [vLookStr 0]
				(Format @buffer
			  		{
						\09\09\09(LOOK\r
						\09\09\09)\r
					}
					@vLookStr
				)
				(Logit @buffer)
			)
			(if [vDoStr 0]
				(Format @buffer
					{
						\09\09\09(DO\r
						\09\09\09)\r
					}
					@vDoStr
				)
				(Logit @buffer)
			)
			(if [vTalkStr 0]
				(Format @buffer
					{
						\09\09\09(TALK\r
						\09\09\09)\r
					}
					@vTalkStr
				)
				(Logit @buffer)
			)
			(Format @buffer
				{
					\09\09\09(else\r
					\09\09\09\09(super doVerb: theVerb)\r
					\09\09\09)\r
					\09\09)\r
					\09)\r
				}
			)
			(Logit @buffer)
		)

		; Close off the object

		(StrCpy @buffer {)\r})
		(Logit @buffer)

		(if (theObj isMemberOf: Feature)
			(theObj dispose:)
		else
			(theObj addToPic:)
		)
		(file
			close:	,
			dispose:
		)
		(DisposeScript FILE)
		(DisposeScript	WRITEFTR)
	)

;; although currently not used
;; this is a useful method for writing out the cast, features list etc.
	(method (writeList theList)
		(theList eachElementDo: #perform: self)
		(CreateObject doit:)
		(DisposeScript WRITEFTR)
	); writeObjects
)


;CLG;ษอออออออออออออออออป
;CLG;บ                 บ
;CLG;บ Approach Dialog บ
;CLG;บ                 บ
;CLG;ศอออออออออออออออออผ
;CLG
;CLG(instance approachD of Dialog
;CLG	(properties
;CLG		name: {aprchD}
;CLG		text: {Approach Verbs}
;CLG	)
;CLG	(method	(init &tmp i ret text1)
;CLG
;CLG		; Give ourself the system window as our window
;CLG		(= window systemWindow)
;CLG
;CLG		; Create the text item for the instructions
;CLG		(= text1
;CLG			((DText new:)
;CLG				text: 		{Select verbs to add},
;CLG				font:			SYSFONT,
;CLG				setSize: 	,
;CLG				moveTo: 		MARGIN MARGIN,
;CLG				yourself:
;CLG			)
;CLG		)
;CLG
;CLG		; Create the selctor
;CLG		(selectorI
;CLG			text: 	verbString,
;CLG			font:		smallFont,
;CLG			setSize:	,
;CLG			moveTo: 	MARGIN (+ MARGIN 12),
;CLG			state: 	dActive
;CLG		)
;CLG
;CLG		; Create the buttons
;CLG		(allBut
;CLG		  	setSize:		,
;CLG		  	moveTo:		(+ MARGIN 90) (+ MARGIN 46)
;CLG		)
;CLG		(clearBut
;CLG			setSize:		,
;CLG			moveTo:		(+ MARGIN 90) (+ MARGIN 61)
;CLG		)
;CLG		(doneBut
;CLG		  	setSize:		,
;CLG		  	moveTo:		(+ MARGIN 90) (+ MARGIN 76)
;CLG		)
;CLG
;CLG		; Put these elements into the dialog and size it.
;CLG		(self
;CLG			add: 		text1 selectorI allBut clearBut doneBut,
;CLG			setSize:
;CLG		)
;CLG
;CLG		; Do the dialog!
;CLG		(= ret
;CLG			(self
;CLG				setSize:	, 
;CLG				center:	,
;CLG				open: 	wTitled -1,
;CLG				doit:
;CLG			)
;CLG		)
;CLG
;CLG		; Now get rid of all the pieces-parts
;CLG		(self dispose:)
;CLG
;CLG		(if ret
;CLG			(for	((= i 0) (= ret 0))
;CLG					(< i NUMITEMS)
;CLG					((++ i))
;CLG				(if (== (StrAt verbString (* i ITEMSIZE)) `>)
;CLG					(|= ret (<< 1 i))
;CLG				)
;CLG			)
;CLG		)
;CLG		(return ret)
;CLG	)
;CLG)


(instance selectorI of DSelector
	(properties
		x 	ITEMSIZE
		y 	NUMITEMS
	)
	(method (handleEvent event &tmp eType eMsg)
		(super handleEvent: event)
		(= eType (event type?))
		(= eMsg	(event message?))
		(if (or	(and	(== eType mouseDown)
			  				(event claimed?)
			  		)
			  		(and	(== eType keyDown)
			  				(== eMsg SPACEBAR)
			  		)
				)
			(if (== (StrAt cursor 0) `>)
				(StrAt cursor 0 SPACEBAR)
			else
				(StrAt cursor 0 `>)
			)
			(self draw:)
			(event claimed: TRUE)
		)
		(return (event claimed?))
	)
)

(instance clearBut of DButton
	(properties
		text			{Clear}
		value			0
		state			dActive
	)
	(method (doit &tmp i)
		(for	((= i 0))
				(< i NUMITEMS)
				((++ i))
			(StrAt verbString (* i ITEMSIZE) SPACEBAR)
		)
		(selectorI draw:)
	)
)

(instance allBut of DButton
	(properties
		text			{__All__}
		value			2
		state			dActive
	)
	(method (doit &tmp i)
		(for	((= i 0))
				(< i NUMITEMS)
				((++ i))
			(StrAt verbString (* i ITEMSIZE) `>)
		)
		(selectorI draw:)
	)
)

(instance doneBut of DButton
	(properties
		text			{_Done_}
		value			1
	)
)

(instance wfWin of Window)

(instance shmFile of File)