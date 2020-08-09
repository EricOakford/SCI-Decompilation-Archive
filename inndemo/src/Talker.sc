;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	TALKER.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1992
;;;;
;;;;	Author: 	Brian K. Hughes & Mark Wilden (original by Mark Hood)
;;;;	Updated: Brian K. Hughes
;;;;
;;;;	Classes:
;;;;		Blink
;;;;		Narrator
;;;;		Talker


(script# TALKER)
(include game.sh)
(use Main)
(use Print)
(use Sync)
(use RandCyc)
(use Motion)
(use Actor)
(use System)


(class Blink kindof Cycle
	(properties
		cycleDir		1
		waitCount	0
		lastCount	0
		waitMin		0
		waitMax		0
	)
	(method (init obj theTime)
		(if argc
			(= waitMin (/ theTime 2))
			(= waitMax (+ theTime waitMin))
	 		(super init: obj)
		else
			(super init:)
		)
	)
	(method (doit &tmp newCel)
		(if waitCount
			(if (> (- gameTime waitCount) 0)
				(= waitCount 0)
				(self init:)
			)
		else
			(= newCel (self nextCel:))
			(if
				(or 
					(> newCel (client lastCel:))
					(< newCel 0)
				)
				(= cycleDir (- cycleDir))
				(self cycleDone:)
			else
				(client cel: newCel)
			)
		)
	)
	(method (cycleDone)
		(if (== cycleDir -1)
			(self init:)
		else
			(= waitCount (+ (Random waitMin waitMax) gameTime))
		)
	)	
)


(class Narrator kindof Prop
	(properties
		x						-1				;-Narrator will center his messages
		y						-1				;/
		caller				NULL
		modNum				-1				;Module number of message file
		disposeWhenDone	TRUE			;Dispose & hide or just dispose
		ticks					NULL
		talkWidth			NULL			;Width of text box
		keepWindow			FALSE			;Require keypress to dispose text box
		modeless				FALSE
		font					0
		cueVal				FALSE			;Return TRUE if right-click or ESC
		initialized			FALSE			;True if we are already inited
		showTitle			FALSE			;TRUE if name should be displayed as title
		color					0				;Foreground color of text
		back					7				;Background color of window

		curVolume			NULL			;Private, for CD use
		saveCursor			NULL			;Private, for CD use
	)

;;;	(methods
;;;		init					;Initialize and adjust volume if necessary
;;;		dispose				;Delete from fastCast and optionally cue caller
;;;		say					;startText or startAudio based on msgType
;;;		sayNext				;say the next message in a sequence, if any
;;;		startText			;Prepare message
;;;		display				;Print message
;;;		startAudio			;Start talker sync'ing to audio
;;;		handleEvent			;Look for ESC, ENTER, mouseDowns
;;;		doit					;A fastCast animation cycle
;;;	)

	(method (init)
		(if (& msgType CD_MSG)
			; Lower the volume of the game music (if any)
			(= curVolume (theGame masterVolume?))
			(if (>= curVolume 6)
				(theGame masterVolume:	(- curVolume 6))
			else
				(theGame masterVolume:	1)
			)
		)

		; Put up a wait cursor and save off the old cursor if it can't be moved
		(if (or 	(and (& msgType CD_MSG) (not modeless))
					(not (HaveMouse))
			)
			(= saveCursor (theGame setCursor: waitCursor TRUE))
		)
		(= gameTime (+ tickOffset (GetTime)))
		(= initialized TRUE)
	)

	(method (say theBuf whoCares)
		;
		; Set up any properties required to say this message & pass the buffer
		;	on to the startText and/or startAudio methods.  'theBuf' will be an
		;	array of message keys if audio

		(if theIconBar
			(theIconBar disable:)
		)

		; If we have not been inited, init the talker
      (if (not initialized)
			(self init:)
		)

		; See if we have a caller
		(= caller
			(if (and (> argc 1) whoCares)
				whoCares
			else
				0
			)
		)

		; Figure out what to do with the message
		(if (& msgType TEXT_MSG)
			(self startText: theBuf)
		)
		(if (& msgType CD_MSG)
			(self startAudio: theBuf)
		)

		(cond
			(modeless
				(mouseDownHandler addToFront: self)
				(keyDownHandler addToFront: self)
				(theDoits add: self)
			)
			((IsObject fastCast)
		 		(fastCast add: self)
			)
			(else
				((= fastCast (EventHandler new:))
					name: {fastCast},
					add: 	self
				)
			)
		)
		(= ticks (+ ticks 60 gameTime))
		(return TRUE)
	)

	(method (startText theBuf &tmp strLength)

		; If there is a CD component to this message as well, get ticks from
		;	there, else get 'em from here
		(if (not (& msgType CD_MSG))
			(= ticks (Max 	240 ; keep window up at least 4 seconds
								(* (* textSpeed 2) (= strLength (StrLen theBuf)))
						)
			)
		)

		(if modelessDialog 
			(modelessDialog dispose:)
		)
		(self display: theBuf)
		(return strLength)
	)

   (method (display theBuf &tmp theWidth theWin)

		; Open a window & display the text

		(if (> (+ x talkWidth) 318)
			(= theWidth (- 318 x))
		else
			(= theWidth talkWidth)
		)

		((= theWin (systemWindow new:))
			color:		color,
			back:			back
		)
		(if (and	(not (HaveMouse))
					(!= theCursor INVIS_CURSOR)
				)
			(= saveCursor theCursor)
			(theGame setCursor: INVIS_CURSOR)
		else
			(= saveCursor 0)
		)
		(if showTitle
			(Print addTitle: name)
		)
		(Print
			window:		theWin,
			posn:			x y,
			font:			font,
			width:		theWidth,
			addText:		theBuf,
			modeless:	TRUE,
			init:
		)
   )

	(method (startAudio theKeys &tmp m n v c s)

		(= m (WordAt theKeys 0))
		(= n (WordAt theKeys 1))
		(= v (WordAt theKeys 2))
		(= c (WordAt theKeys 3))
		(= s (WordAt theKeys 4))

		; Actually do the message stuff for CD messages
		(= ticks (DoAudio Play m n v c s))
	)

	(method (doit)

		; Do real-time ticks stuff & see if we need to dispose yet or not

		; If we're out of ticks (and DoAudio Loc is -1 IF CD)
		(if (!= ticks -1)
			(if (and	(> (- gameTime ticks) 0)
						(if (& msgType CD_MSG)
					  		(== (DoAudio Loc) -1)
						else
							TRUE
						)
					)
				(if (or 	(not keepWindow)
							(& msgType CD_MSG)
					)
   				(self dispose: disposeWhenDone)
        			(return FALSE)
         	)
			)
		)
		(return TRUE)
	)

	(method (handleEvent event)
		(cond
			((event claimed?))
			((== ticks -1)
				(return FALSE)
			)
			(else
				(if (not cueVal)
					(switch (event type?)
						(joyDown
							(= cueVal FALSE)
						)
						(mouseDown
							(= cueVal (& (event modifiers?) shiftDown))
						)
						(keyDown
							(= cueVal (== (event message?) ESC))
						)
					)
				)

				(if (or
						(& (event type?) (| userEvent joyDown mouseDown))
						(and
							(& (event type?) keyDown)
							(OneOf (event message?) ENTER ESC)
						)
					)
					(event claimed: TRUE)
					(self dispose: disposeWhenDone)
				)
			)
		)
	)

	(method (dispose dWD)
		; This is so we can still get doits but not try to mess with gameTime
		(= ticks -1)

		; If disposeWhenDone was passed and is TRUE
		;		- OR -
		;	nothing was passed, do the full dispose

		(if (or (not argc) dWD)
			
			(cond
				(modeless
					(keyDownHandler delete: self)
					(mouseDownHandler delete: self)
					(theDoits delete: self)
				)
				((and fastCast
						(fastCast contains: self)
					)
					(fastCast delete: self)
					(if (fastCast isEmpty?)
						(fastCast dispose:)
						(= fastCast 0)
					)
				)
			)

			(if (& msgType CD_MSG)
			  	(DoAudio Stop)
			)

			; Reset the modNum only if disposing for good
         (= modNum -1)
			(= initialized FALSE)
		)

		(if modelessDialog
			(modelessDialog dispose:)
		)

		(if (& msgType CD_MSG)
			(theGame masterVolume: curVolume)
		)
		(if (or 	(and (& msgType CD_MSG) (not modeless))
					(not (HaveMouse))
			)
			(theGame setCursor: saveCursor)
		)		

		; If we are supposed to cue someone, do so & pass along the cueVal,
		;	which indicates whether we hit an ESC or right-click.  Whether
		;	or not the caller cares is entirely up to it.
		(if caller
			(caller cue: cueVal)
		)

		(= cueVal NULL)

		; in leu of super dispose:
		(DisposeClone self)
	)
)



(class Talker kindof Narrator
	(properties
		bust					NULL
		eyes					NULL
		mouth					NULL
		viewInPrint			FALSE
		textX					0			;-top left of text box, relative to talker
		textY					0			;/
		useFrame				FALSE
		talkWidth			318
		blinkSpeed			100		;# ticks to wait before blinking again,
											;	randomized from 50% to 150%
	)

;;;	(methods 
;;;		cycle				;cycle mouth and eyes and redraw them
;;;		setSize			;set nsRect for view
;;;		show				;draw all components of talker on screen
;;;		hide				;erase talker from screen
;;;	)

	(method (init bustView prop syncedProp)

		; Set up parameters (if any) and invoke setSize:

		(if argc
			(= bust bustView)
			(if (> argc 1)
				(= eyes prop)
				(if (> argc 2)
					(= mouth syncedProp)
				)
			)
		)

		; Sets the saveBits without showing talker yet
		(self	setSize:)

		(super init:)
	)

	(method (setSize)

		; Calculates the largest background size needed for underbits

		(= nsLeft x)
		(= nsTop y)

		(= nsRight 	
			(+ nsLeft 
				(Max
					(if view
						(CelWide view loop cel)
					else
						0
					)
					(if (IsObject bust)
						(+ (bust nsLeft?) (CelWide (bust view?)(bust loop?)(bust cel?)))
					)
					(if (IsObject eyes)
						(+ (eyes nsLeft?) (CelWide (eyes view?)(eyes loop?)(eyes cel?)))
					)
					(if (IsObject mouth)
						(+ (mouth nsLeft?) (CelWide (mouth view?)(mouth loop?)(mouth cel?)))
					)
				)
			)
		)
		(= nsBottom 
			(+ nsTop 
				(Max
					(if view 
						(CelHigh view loop cel)
					else
						0
					)
					(if (IsObject bust)
						(+ (bust nsTop?) (CelHigh (bust view?)(bust loop?)(bust cel?)))
					)
					(if (IsObject eyes)
						(+ (eyes nsTop?) (CelHigh (eyes view?)(eyes loop?)(eyes cel?)))
					)
					(if (IsObject mouth)
						(+ (mouth nsTop?) (CelHigh (mouth view?)(mouth loop?)(mouth cel?)))
					)
				)
			)

		)
	)

	(method (show &tmp pnv)

		; Actually draw the talker on the screen

		(if (not underBits)
			(= underBits (Graph GSaveBits nsTop nsLeft nsBottom nsRight VMAP))
		)
		(= pnv (PicNotValid))
		(PicNotValid TRUE)
		(if bust
			(DrawCel	
				(bust view?) (bust loop?) (bust cel?) 
				(+ (bust nsLeft?) nsLeft) (+ (bust nsTop?) nsTop)
				-1
			)
		)
		(if eyes
			(DrawCel	
				(eyes	view?) (eyes loop?) (eyes cel?)	
				(+ (eyes nsLeft?) nsLeft) (+ (eyes nsTop?) nsTop)
				-1
			)
		)
		(if mouth
			(DrawCel	
				(mouth view?) (mouth loop?) (mouth cel?)	
				(+ (mouth nsLeft?) nsLeft) (+ (mouth nsTop?) nsTop) 
				-1
			)
		)
		(DrawCel view loop cel nsLeft nsTop -1)
		(Graph GShowBits nsTop nsLeft nsBottom nsRight VMAP)
		(PicNotValid pnv)
	)

	(method (say)

		(if (and	(> view 0) (not underBits))
			(self init:)
		)

		(super say: &rest)
	)

	(method (startText &tmp theLen)

		(if (not viewInPrint)
			(self show:)
		)

		(= theLen (super startText: &rest))

		(if mouth
			(mouth setCycle: RandCycle (* 4 theLen) NULL TRUE)
		)
		(if (and	eyes
					(not (eyes cycler?))
				)
			(eyes setCycle: Blink blinkSpeed)
		)
	)

   (method (display theBuf &tmp theLoop theWidth textLeft theWin)

		; Open a window & display the text, with an icon for the talker if
		;	it is a 'viewInPrint' talker

		((= theWin (systemWindow new:))
			color:		color,
			back:			back
		)
		(if (and	(not (HaveMouse))
					(!= theCursor INVIS_CURSOR)
				)
			(= saveCursor theCursor)
			(theGame setCursor: INVIS_CURSOR)
		else
			(= saveCursor 0)
		)
      (if viewInPrint
			(= theLoop (if useFrame loop else (bust loop?)))
			(if showTitle
				(Print addTitle: name)
			)
			(Print
				window:		theWin,
				posn:			x y,
				modeless:	TRUE,
				font:			font,
				addText:		theBuf (+ 5 (CelWide view theLoop cel)) 0,
				addIcon:		view theLoop cel 0 0,
				init:
			)
      else
			; If we didn't specify textX and textY, try to place to the right
			;	of the talker's portrait
			(if (not (+ textX textY))
				(= textX (+ (- nsRight nsLeft) 5))
			)

			; Check to see if the text width would be off the screen
			(= textLeft (+ nsLeft textX))
			(if (> (+ textLeft talkWidth) 318)		; stop at right edge
				(= theWidth (- 318 textLeft))
			else
				(= theWidth talkWidth)
			)
			(if showTitle
				(Print addTitle: name)
			)
			(Print
				window:		theWin,
				posn:			(+ x textX) (+ y textY),
				modeless:	TRUE,
				font:			font,
				width:		theWidth,
				addText:		theBuf,
				init:
			)
		)
   )

	(method (startAudio theKeys &tmp m n v c s)

		(self show:)

		(super startAudio: theKeys)

		(if mouth
			(= m (WordAt theKeys 0))
			(= n (WordAt theKeys 1))
			(= v (WordAt theKeys 2))
			(= c (WordAt theKeys 3))
			(= s (WordAt theKeys 4))
			(mouth setCycle: MouthSync m n v c s)
		)
		(if (and	eyes
					(not (eyes cycler?))
				)
		  	(eyes	setCycle: Blink blinkSpeed)
		)
	)

	(method (cycle obj &tmp theCel [str 100])

		; Make the eyes and mouth (if not CD) cycle

		(if (and obj (obj cycler?))
			(= theCel 	(obj cel?))
			((obj cycler?)		doit:)
			(if (!= theCel (obj cel?))
				(DrawCel	
					(obj	view?)
					(obj	loop?)
					(obj	cel?)	
					(+ (obj nsLeft?) nsLeft)
					(+ (obj nsTop?) nsTop)
					-1
				)
				(obj 
					nsRight: 
						(+ 
							(obj nsLeft?) 
							(CelWide (obj view?) (obj loop?)  (obj cel?))
						)
				)
				(obj 
					nsBottom: 
						(+ 
							(obj nsTop?) 
							(CelHigh (obj view?) (obj loop?)  (obj cel?))
						)
				)
		 		(Graph GShowBits 
					(+ (obj nsTop?) 		nsTop)
					(+ (obj nsLeft?) 		nsLeft)
					(+ (obj nsBottom?) 	nsTop)
					(+ (obj nsRight?) 	nsLeft)
					VMAP
				)
			)
		)
	)

	(method (doit)

		(if (super doit:)
			; Cycle the mouth if we still have time left
		   (if mouth
				(self cycle: mouth)
			)
		)

      ; continually cycle until box is cleared.
		(if eyes
			(self cycle: eyes)
		)
	)

	(method (hide)
		(Graph GRestoreBits underBits)
		(=	underBits 0)
		(Graph GReAnimate nsTop nsLeft nsBottom nsRight)
		(if theIconBar
			(theIconBar enable:)
		)
	)

	(method (dispose dWD)

		; Reset the mouth to cel 0
		(if (and mouth	underBits)
			(mouth cel: 0)
			(DrawCel	
				(mouth view?) (mouth loop?) 0	
				(+ (mouth nsLeft?) nsLeft) (+ (mouth nsTop?) nsTop)
				-1
			)
		)

		; We want to remove the mouth synch because the CD guys say we
		;	can really only have one at a time & another talker can't use
		;	it unless we give it up


		(if (and mouth (mouth cycler?))
			(if ((mouth cycler?) respondsTo: #cue)
				((mouth cycler?)  cue:)
			)
			(mouth setCycle: 0)
		)

		(if (or (not argc) dWD)
			(if (and eyes underBits)
				(eyes
					setCycle:	0,
					cel:			0
				)
				(DrawCel	
					(eyes view?) (eyes loop?) 0	
					(+ (eyes nsLeft?) nsLeft) (+ (eyes nsTop?) nsTop)
					-1
				)
			)
			(self hide:)
		)

		(super dispose: dWD)
	)
)
