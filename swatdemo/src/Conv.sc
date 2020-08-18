;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	CONV.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1992
;;;;
;;;;	Author:	Brian K. Hughes
;;;;	Updated:	Brian K. Hughes
;;;;
;;;;	Handles all messages and actions required to make a full conversation.
;;;;		Allows animation between messages as well.
;;;;
;;;;	Classes:
;;;;		MessageObj
;;;;		Conversation


;;;;	How the Conversation class works:
;;;;	  
;;;;	  a Conversation is just a list of conversation elements, those being
;;;;	  messages, scripts, and other objects.  Once assembled, the conver-
;;;;	  sation walks through each element in the list and performs the
;;;;	  appropriate function:
;;;;	  
;;;;	  		MessageObj 	- the message is displayed, by the appropriate talker
;;;;	  		Script		- the script is run to its completion
;;;;	  		object		- the object's doit method is called
;;;;	  
;;;;	  Note that in the case of other objects, it is the programmer's respon-
;;;;	  sibility to be sure that the conversation is cued when the code or
;;;;	  object has completed.  The conversation's ID will be passed to the
;;;;	  object's doit method.
;;;;	  
;;;;	  
;;;;	The ADD method:
;;;;	  
;;;;	  The add method of Conversation has been redefined.  In addition to
;;;;	  the normal functionality, you may also pass message parameters directly
;;;;	  to the add method, as in:
;;;;	  
;;;;	  			(myConv add: module noun [verb [case [seq [x y [font]]]]])
;;;;	  
;;;;	  
;;;;	The LOAD method:
;;;;	  
;;;;	  The load method of Conversation allows for build a conversation from
;;;;	  a local array.  When passed the address of an array, the load method
;;;;	  will create dynamic MessageObj objects with the information from the
;;;;	  array and add them to the Conversation.  The format of a conversation
;;;;	  array is as follows:
;;;;	  
;;;;	  		myArray = [ module noun verb case seq x y font
;;;;	  						module noun verb case seq x y font
;;;;	  						...
;;;;	  					 ]
;;;;	  
;;;;	  If 'module' is -1, it will be set to the curRoomNum.  If the message
;;;;	  should display all sequences associated with the case, enter 0 in the
;;;;	  sequence position in the array.  If the x and y should default to the
;;;;	  talker's x and y, enter 0's in their positions as well.  If the font
;;;;	  should default to the userFont, enter a -1 in its position.


(script# CONV)
(include game.sh)
(use Main)
(use Print)
(use System)


(class MessageObj kindof Object
	(properties
		modNum		-1	;¿
		noun			0	;³
		verb			0	;Ã Message parameters
		case			0	;³
		sequence		0	;Ù
		whoSays		0	;Ä	who is saying us - only important for dispose
		client		0	;Ä	who owns us - usually conversation
		caller		0	;Ä	who we're gonna cue
		font			0	;Ä	font for message - default to talker's font
		x				0	;Â position of message - default to talker's x & y
		y				0	;Ù
	)
;;;	(methods
;;;		showSelf
;;;	)

	(method (showSelf who)
		;
		; Set up the message to the Messager & make him talk to the talker

		; Find out who the talker is
		(= whoSays
			(messager findTalker: (Message MsgGet modNum noun verb case (if sequence sequence else 1)))
		)

		(cond
			((== whoSays -1)
				(caller cue:)
			)
			((not whoSays)
				(Print
					addTextF: {<MessageObj> Message not found: %d - %d, %d, %d, %d}
									modNum noun verb case sequence,
					init:
				)
			)
			(else
				; If we have a special font, tell the talker to use it
				(if font
					(whoSays font: font)
				)

				; If we have an x & y specified, tell the talker to use it
				(if (or x y)
					(whoSays
						x:		x,
						y:		y
					)
				)
			
				; Now the messager takes over
				(messager say: noun verb case sequence caller modNum)
			)
		)
	)
)


(class Conversation kindof List
	(properties
		script	0
		curItem	-1
		caller	0
	)
;;;	(methods
;;;		cue				;process an element of the conversation
;;;		setScript		;set a script to run
;;;		load				;load from a conversation array
;;;	)

	(method (init who)
		;
		; Set up the conversation and get us started

		; Add ourselves to the doits list so we get doits

		(= curItem -1)
		(if (and argc who)
			(= caller who)
		)
		(theDoits add: self)
		(self cue:)
	)

	(method (dispose &tmp toCall)
		;
		; Clean up any scripts left hanging, dispose any talkers left hanging,
		;	and clean ourselves up

		(self eachElementDo: #perform cleanCode)
		(theDoits delete: self)
		(if script
			(= script 0)
		)
		(= toCall caller)
		(super dispose:)
		(if toCall
			(toCall cue:)
		)
	)

	(method (doit)
		;
		; Let scripts get a doit

		(if script
			(script doit:)
		)
	)

	(method (cue killIt &tmp obj theClient)
		;
		; Grab the next conversation element & process it, unless killed
		; WARNING:	The default behavior of the Script class is to pass its
		;				register to its client's cue method!  Override the dispose
		;				method of scripts used in Conversations and set the register
		;				property to 0.

		(if (or 	(and argc killIt)
					(== (++ curItem) size)
				)
			(self dispose:)
		else
			(= obj (self at: curItem))
			(cond

				; Current object is a MessageObj

				((obj isKindOf: MessageObj)
					(obj
						caller:		self,
						showSelf:
					)
				)

				; Current object is a Script

				((obj isKindOf: Script)
					(self setScript: obj self)
				)

				; Current object is miscellaneous object

				(else
					(obj doit: self)
				)
			)
		)
	)

	(method (setScript newScript)
		;
		; Set a script to the Conversation that will cue us when done

		(if script
			(script dispose:)
		)
		(if newScript
			(newScript init: self &rest)
		)
	)

	(method (load theConv
						&tmp theMod theNoun theVerb theCase theSeq theX theY theFont i
				)
		;
		; Load the Conversation from a local array.  Format of array must be:
		;	module noun verb case seq x y font.
		;		module = -1 indicates the message file for the current room
		;		theX   = 0  indicates use talker's x
		;		theY   = 0  indicates use talker's y
		;		font   = 0  indicates use talker's font

		(= theMod	(theConv at: 0))
		(= theNoun	(theConv at: 1))
		(= theVerb	(theConv at: 2))
		(= theCase	(theConv at: 3))
		(= theSeq	(theConv at: 4))
		(= theX 		(theConv at: 5))
		(= theY 		(theConv at: 6))
		(= theFont	(theConv at: 7))
		(= i 7)

		(while theMod
			(if (== theMod -1)
				(= theMod curRoomNum)
			)

			(self add: theMod theNoun theVerb theCase theSeq theX theY theFont)

			(= theMod	(theConv at: (++ i)))
			(= theNoun 	(theConv at: (++ i)))
			(= theVerb	(theConv at: (++ i)))
			(= theCase	(theConv at: (++ i)))
			(= theSeq	(theConv at: (++ i)))
			(= theX 		(theConv at: (++ i)))
			(= theY 		(theConv at: (++ i)))
			(= theFont 	(theConv at: (++ i)))
		)
	)

	(method (add args &tmp aMod aNoun aVerb aCase aSeq theX theY aFont)
		;
		; Adds an element to the Conversation.  If passed an object ID, add
		;	functions normally.  If passed a set of message parameters, add will
		;	create a dynamic MessageObj with those parameters.  Parameters are:
		;
		;			module# noun [verb [case [seq [x y [font]]]]]

		(= aMod (= aNoun (= aVerb (= aCase (= aSeq 0)))))
		(= theX (= theY (= aFont 0)))

		(if (> argc 1)
			(= aMod [args 0])
			(if (== aMod -1)
				(= aMod curRoomNum)
			)
			(if (> argc 1)
				(= aNoun [args 1])
				(if (> argc 2)
					(= aVerb [args 2])
					(if (> argc 3)
						(= aCase [args 3])
						(if (> argc 4)
							(= aSeq [args 4])
							(if (> argc 5)
								(= theX [args 5])
								(if (> argc 6)
					 				(= theY [args 6])
									(if (> argc 7)
										(= aFont [args 7])
									)
								)
							)
						)
					)
				)
			)
			(if (> argc 1)
				(super add:
					((MessageObj new:)
						modNum:		aMod,
						noun:			aNoun,
						verb:			aVerb,
						case:			aCase,
						sequence:	aSeq,
						x:				theX,
						y:				theY,
						font:			aFont,
						yourself:
					)
				)
			)
	  	else
	  		(super add: args &rest)
		)
	)
)


; This is just a cleanup tool that takes care of any scripts hanging around
;	or talkers that have not been fully disposed

(instance cleanCode of Code
	(method (doit obj &tmp tkr)
		(if (obj isKindOf: Script)
		  	(obj caller: 0)
		)
		(if (obj isKindOf: MessageObj)
			(= tkr (obj whoSays?))
;			(if (tkr underBits?)
				(tkr dispose: TRUE)
;			)
		)
	)
)
